package com.services.error.handler.handlers.generic;

import com.google.gson.Gson;
import com.services.error.handler.enums.DefaultExceptionCodesEnum;
import com.services.error.handler.enums.KnownExceptionsEnum;
import com.services.error.handler.factories.ObjectFactory;
import com.services.error.handler.handlers.DefaultExceptionHandler;
import com.services.error.handler.handlers.IExceptionHandler;
import com.services.error.handler.responses.ErrorResponse;
import com.services.error.handler.services.DummyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author shashankmittal
 * @created 11/04/20
 */
public class DefaultExceptionHandlerTest {

    private final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandlerTest.class);

    private IExceptionHandler handler;

    private DummyService service;

    private final Gson GSON = new Gson();

    @Before
    public void setup() {
        service = ObjectFactory.getDummyService();
        handler = ObjectFactory.getDefaultExceptionHandler();
    }

    @Test
    public void testDefaultHandlerWithoutErrorMapperForKnownErrorCode() {
        try {
            service.throwExceptionWithDisplayMessage();
        } catch (Exception e) {
            ErrorResponse errorResponse = handler.handleException(e);
            logger.info("Error Response is [{}]", GSON.toJson(errorResponse));

            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getCode(), errorResponse.getErrorCode());
            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getMessage(), errorResponse.getErrorMessage());
            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getDisplayMessage(), errorResponse.getDisplayMessage());
        }
    }

    @Test
    public void testDefaultHandlerWithoutErrorMapperForKnownErrorCodeAndMetadata() {
        try {
            service.throwExceptionWithDisplayMessageAndMetadata();
        } catch (Exception e) {
            ErrorResponse<Map<String, String>> errorResponse = handler.handleException(e);
            logger.info("Error Response is [{}]", GSON.toJson(errorResponse));

            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getCode(), errorResponse.getErrorCode());
            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getMessage(), errorResponse.getErrorMessage());
            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getDisplayMessage(), errorResponse.getDisplayMessage());
            Assert.assertEquals(1, errorResponse.getMetadata().keySet().size());
        }
    }

    @Test
    public void testDefaultHandlerWithErrorMapperForKnownErrorCode() {
        handler = new DefaultExceptionHandler(ObjectFactory.getExceptionProcessor(),
                ObjectFactory.getResponseGeneratorWithErrorMapper());
        try {
            service.throwExceptionWithoutDisplayMessage();
        } catch (Exception e) {
            ErrorResponse errorResponse = handler.handleException(e);
            logger.info("Error Response is [{}]", GSON.toJson(errorResponse));

            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getCode(), errorResponse.getErrorCode());
            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getMessage(), errorResponse.getErrorMessage());
            Assert.assertEquals(ObjectFactory.getErrorMapper().getErrorMapping(errorResponse.getErrorCode()),
                    errorResponse.getDisplayMessage());
        }
    }

    @Test
    public void testDefaultHandlerWithErrorMapperForUnknownErrorCode() {
        handler = new DefaultExceptionHandler(ObjectFactory.getExceptionProcessor(),
                ObjectFactory.getResponseGeneratorWithErrorMapper());
        try {
            service.throwUnknownExceptionWithoutDisplayMessage();
        } catch (Exception e) {
            ErrorResponse errorResponse = handler.handleException(e);
            logger.info("Error Response is [{}]", GSON.toJson(errorResponse));

            Assert.assertEquals("404", errorResponse.getErrorCode());
            Assert.assertEquals("Record Not Found", errorResponse.getErrorMessage());
            Assert.assertEquals(DefaultExceptionCodesEnum.DEFAULT_BUSINESS_EXCEPTION.getErrorMessage(),
                    errorResponse.getDisplayMessage());
        }
    }

    @Test
    public void testDefaultHandlerWithTasks() {
        handler = ObjectFactory.getDefaultExceptionHandler();
        handler.setTasks(ObjectFactory.getTasks());
        try {
            service.throwExceptionWithDisplayMessage();
        } catch (Exception e) {
            ErrorResponse errorResponse = handler.handleException(e);
            logger.info("Error Response is [{}]", GSON.toJson(errorResponse));

            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getCode(), errorResponse.getErrorCode());
            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getMessage(), errorResponse.getErrorMessage());
            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getDisplayMessage(), errorResponse.getDisplayMessage());
        }
    }

    @Test
    public void testDefaultHandlerForNPE() {
        handler = ObjectFactory.getDefaultExceptionHandler();
        try {
            service.throwNullPointerException();
        } catch (Exception e) {
            ErrorResponse errorResponse = handler.handleException(e);
            logger.info("Error Response is [{}]", GSON.toJson(errorResponse));

            Assert.assertEquals(DefaultExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorCode(), errorResponse.getErrorCode());
            Assert.assertEquals(DefaultExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorMessage(), errorResponse.getDisplayMessage());
        }
    }

    @Test
    public void testDefaultHandlerWithPackageFilterForNPE() {
        handler = ObjectFactory.getDefaultExceptionHandlerWithPackageFilter("com.services.error.handler");
        try {
            service.throwNullPointerException();
        } catch (Exception e) {
            ErrorResponse errorResponse = handler.handleException(e);
            logger.info("Error Response is [{}]", GSON.toJson(errorResponse));


            Assert.assertEquals(DefaultExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorCode(), errorResponse.getErrorCode());
            Assert.assertEquals(DefaultExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorMessage(), errorResponse.getDisplayMessage());
        }
    }
}
