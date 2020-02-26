//package com.services.error.handler.test;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.services.error.handler.constants.ExceptionCodesEnum;
//import com.services.error.handler.handler.ErrorResponse;
//import com.services.error.handler.handler.IExceptionHandler;
//
///**
// * @author fcsm18808 Shashank Mittal
// * @created Jan 28, 2019
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = ErrorHandlerTestConfig.class)
//public class SystemExceptionHandlerTest {
//
//	@Autowired
//	SystemMonitoredService systemMonitoredService;
//
//	@Autowired
//	IExceptionHandler exceptionHandlerDefault;
//
//	@Autowired
//	IExceptionHandler exceptionHandlerWithPackageFilter;
//
//	/**
//	 * This test case will test when a method throws null pointer exception not
//	 * known to the user. The Exception Handler is the default Exception
//	 * Handler.
//	 */
//	@Test
//	public void testNullPointerException() {
//		try {
//			systemMonitoredService.throwNullPointerException();
//		} catch (Exception e) {
//			ErrorResponse handleException = exceptionHandlerDefault.handleException(e);
//			assertErrorResponse(handleException, ExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorCode());
//		}
//	}
//
//	/**
//	 * This test case will test when a method throws null pointer exception not
//	 * known to the user. The exception handler is initialized with a package
//	 * filter that will only allow the classes that will fall into the specified
//	 * packages and thus skipping irrelevant packages from output.
//	 */
//	@Test
//	public void testNullPointerExceptionWithPackageFilter() {
//		try {
//			systemMonitoredService.throwNullPointerException();
//		} catch (Exception e) {
//			ErrorResponse handleException = exceptionHandlerWithPackageFilter.handleException(e);
//			assertErrorResponse(handleException, ExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorCode());
//		}
//	}
//
//	/**
//	 * This test case will test when a method throws a user created exception.
//	 * The exception handler is initialized with a package filter that will only
//	 * allow the classes that will fall into the specified packages and thus
//	 * skipping irrelevant packages from output. The UserMessage will be null
//	 * because no mapping is defined in Configuration.
//	 */
//	@Test
//	public void testKnownSystemException() {
//		try {
//			systemMonitoredService.throwKnownSystemException();
//		} catch (Exception e) {
//			ErrorResponse handleException = exceptionHandlerWithPackageFilter.handleException(e);
//			assertErrorResponse(handleException, ExceptionCodesEnum.DEFAULT_BASE_EXCEPTION.getErrorCode());
//			Assert.assertNotEquals(null, handleException.getUserMessage());
//		}
//	}
//
//	/**
//	 * This test case will test when a method throws a user created exception.
//	 * The exception handler is initialized with a package filter that will only
//	 * allow the classes that will fall into the specified packages and thus
//	 * skipping irrelevant packages from output. The UserMessage should not be
//	 * null if proper mapping is given in the mapping.
//	 */
//	@Test
//	public void testKnownSystemExceptionWithErrorMapper() {
//		try {
//			systemMonitoredService.throwKnownSystemException();
//		} catch (Exception e) {
//			ErrorResponse handleException = exceptionHandlerWithPackageFilter.handleException(e);
//			assertErrorResponse(handleException, ExceptionCodesEnum.DEFAULT_BASE_EXCEPTION.getErrorCode());
//			Assert.assertNotEquals(null, handleException.getUserMessage());
//		}
//	}
//
//	private void assertErrorResponse(ErrorResponse errorResponse, String errorCode) {
//		Assert.assertEquals(errorCode, errorResponse.getErrorCode());
//		Assert.assertNotEquals(null, errorResponse.getErrorMessage());
//	}
//}
