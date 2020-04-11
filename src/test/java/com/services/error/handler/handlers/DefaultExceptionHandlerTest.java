package com.services.error.handler.handlers;

import com.services.error.handler.constants.DefaultExceptionCodesEnum;
import com.services.error.handler.enums.KnownExceptionsEnum;
import com.services.error.handler.factories.ObjectFactory;
import com.services.error.handler.responses.ErrorResponse;
import com.services.error.handler.services.DummyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shashankmittal
 * @created 11/04/20
 */
public class DefaultExceptionHandlerTest {

    private final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandlerTest.class);

    private IExceptionHandler handler;

    private DummyService service;

    @Before
    public void setup() {
        service = ObjectFactory.getDummyService();
        handler = ObjectFactory.getDefaultExceptionHandler();
    }

    @Test
    public void testDefaultHandler() {
        try {
            service.throwExceptionWithDisplayMessage();
        } catch (Exception e) {
            ErrorResponse errorResponse = handler.handleException(e);
            logger.info("Error Response is [{}]", errorResponse);

            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getCode(), errorResponse.getErrorCode());
            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getMessage(), errorResponse.getErrorMessage());
            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getDisplayMessage(), errorResponse.getDisplayMessage());
        }
    }

    @Test
    public void testDefaultHandlerWithErrorMapper() {
        handler = new DefaultExceptionHandler(ObjectFactory.getExceptionProcessor(),
                ObjectFactory.getResponseGeneratorWithErrorMapper());
        try {
            service.throwExceptionWithoutDisplayMessage();
        } catch (Exception e) {
            ErrorResponse errorResponse = handler.handleException(e);
            logger.info("Error Response is [{}]", errorResponse);

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
            logger.info("Error Response is [{}]", errorResponse);

            Assert.assertEquals("404", errorResponse.getErrorCode());
            Assert.assertEquals("Record Not Found", errorResponse.getErrorMessage());
            Assert.assertEquals(DefaultExceptionCodesEnum.DEFAULT_BUSINESS_EXCEPTION.getErrorMessage(),
                    errorResponse.getDisplayMessage());
        }
    }

    @Test
    public void testDefaultHandlerWithTasks() {
        DefaultExceptionHandler defaultExceptionHandler = ObjectFactory.getDefaultExceptionHandler();
        defaultExceptionHandler.setTasks(ObjectFactory.getTasks());
        handler = defaultExceptionHandler;
        try {
            service.throwExceptionWithDisplayMessage();
        } catch (Exception e) {
            ErrorResponse errorResponse = handler.handleException(e);
            logger.info("Error Response is [{}]", errorResponse);

            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getCode(), errorResponse.getErrorCode());
            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getMessage(), errorResponse.getErrorMessage());
            Assert.assertEquals(KnownExceptionsEnum.ENTITY_NOT_FOUND.getDisplayMessage(), errorResponse.getDisplayMessage());
        }
    }
}
