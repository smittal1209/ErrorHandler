package com.services.error.handler.handlers;

import com.services.error.handler.constants.DefaultExceptionCodesEnum;
import com.services.error.handler.factories.ObjectFactory;
import com.services.error.handler.responses.ErrorResponse;
import com.services.error.handler.services.SystemMonitoredService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author shashankmittal
 * @created 21/03/20
 */
public class SystemExceptionHandlerTest {

    private SystemMonitoredService systemMonitoredService;

    private IExceptionHandler exceptionHandlerDefault;

    private IExceptionHandler exceptionHandlerWithPackageFilter;

    @Before
    public void setup() {
        systemMonitoredService = ObjectFactory.systemMonitoredService();
    }

    /**
     * This test case will test when a method throws null pointer exception not
     * known to the user. The Exception Handler is the default Exception
     * Handler.
     */
    @Test
    public void testNullPointerException() {
        try {
            systemMonitoredService.throwNullPointerException();
        } catch (Exception e) {
            ErrorResponse handleException = exceptionHandlerDefault.handleException(e);
            assertErrorResponse(handleException, DefaultExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorCode());
        }
    }

    /**
     * This test case will test when a method throws null pointer exception not
     * known to the user. The exception handler is initialized with a package
     * filter that will only allow the classes that will fall into the specified
     * packages and thus skipping irrelevant packages from output.
     */
    @Test
    public void testNullPointerExceptionWithPackageFilter() {
        try {
            systemMonitoredService.throwNullPointerException();
        } catch (Exception e) {
            ErrorResponse handleException = exceptionHandlerWithPackageFilter.handleException(e);
            assertErrorResponse(handleException, DefaultExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorCode());
        }
    }

    /**
     * This test case will test when a method throws a user created exception.
     * The exception handler is initialized with a package filter that will only
     * allow the classes that will fall into the specified packages and thus
     * skipping irrelevant packages from output. The UserMessage will be null
     * because no mapping is defined in Configuration.
     */
    @Test
    public void testKnownSystemException() {
        try {
            systemMonitoredService.throwKnownSystemException();
        } catch (Exception e) {
            ErrorResponse handleException = exceptionHandlerWithPackageFilter.handleException(e);
            assertErrorResponse(handleException, DefaultExceptionCodesEnum.DEFAULT_BASE_EXCEPTION.getErrorCode());
            Assert.assertNotEquals(null, handleException.getDisplayMessage());
        }
    }

    /**
     * This test case will test when a method throws a user created exception.
     * The exception handler is initialized with a package filter that will only
     * allow the classes that will fall into the specified packages and thus
     * skipping irrelevant packages from output. The UserMessage should not be
     * null if proper mapping is given in the mapping.
     */
    @Test
    public void testKnownSystemExceptionWithErrorMapper() {
        try {
            systemMonitoredService.throwKnownSystemException();
        } catch (Exception e) {
            ErrorResponse handleException = exceptionHandlerWithPackageFilter.handleException(e);
            assertErrorResponse(handleException, DefaultExceptionCodesEnum.DEFAULT_BASE_EXCEPTION.getErrorCode());
            Assert.assertNotEquals(null, handleException.getDisplayMessage());
        }
    }

    private void assertErrorResponse(ErrorResponse errorResponse, String errorCode) {
        Assert.assertEquals(errorCode, errorResponse.getErrorCode());
        Assert.assertNotEquals(null, errorResponse.getErrorMessage());
    }
}
