//package com.services.error.handler.test;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.services.error.handler.handler.ErrorResponse;
//import com.services.error.handler.handler.IExceptionHandler;
//
///**
// * @author fcsm18808 Shashank Mittal
// * @created Jan 28, 2019
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = ErrorHandlerTestConfig.class)
//public class BusinessExceptionHandlerTest {
//
//	private final Logger logger = LogManager.getLogger(BusinessExceptionHandlerTest.class);
//	@Autowired
//	BusinessMonitoredService businessMonitoredService;
//
//	@Autowired
//	IExceptionHandler exceptionHandlerDefault;
//
//	@Autowired
//	IExceptionHandler exceptionHandlerWithPackageFilter;
//
//	/**
//	 * This test will test a known business exception thrown within the
//	 * application with default exception handler.
//	 */
//	@Test
//	public void testKnownBusinessException() {
//		try {
//			businessMonitoredService.throwKnownBusinessException();
//		} catch (Exception e) {
//			ErrorResponse handleException = exceptionHandlerDefault.handleException(e);
//			logger.info("Known Business Exception Error Response :  " + handleException);
//			Assert.assertNotEquals(null, handleException.getErrorMessage());
//			Assert.assertNotEquals(null, handleException.getErrorCode());
//		}
//	}
//
//	/**
//	 * This test will test a known business exception thrown within the
//	 * application with exception handler with error mapping. The exception code
//	 * will be mapped to the user message as defined in the error mapping.
//	 */
//	@Test
//	public void testKnownBusinessExceptionWithErrorMapper() {
//		try {
//			businessMonitoredService.throwKnownBusinessException();
//		} catch (Exception e) {
//			ErrorResponse handleException = exceptionHandlerDefault.handleException(e);
//			logger.info("Known Business Exception with User Message Error Response : " + handleException);
//			Assert.assertNotEquals(null, handleException.getErrorMessage());
//			Assert.assertNotEquals(null, handleException.getErrorCode());
//			Assert.assertNotEquals(null, handleException.getUserMessage());
//		}
//	}
//
//	/**
//	 * This test will test a unknown business exception thrown within the
//	 * application with default exception handler.
//	 */
//	@Test
//	public void testUnknownBusinessException() {
//		try {
//			businessMonitoredService.throwUnknownBusinessException();
//		} catch (Exception e) {
//			ErrorResponse handleException = exceptionHandlerDefault.handleException(e);
//			logger.info("Unknown Business Exception Error Response : " + handleException);
//			Assert.assertNotEquals(null, handleException.getErrorMessage());
//			Assert.assertNotEquals(null, handleException.getErrorCode());
//		}
//	}
//
//	/**
//	 * This test will test a unknown business exception thrown within the
//	 * application with exception handler with error mapping. The exception code
//	 * will be mapped to the user message as defined in the error mapping.
//	 */
//	@Test
//	public void testUnknownBusinessExceptionWithErrorMapper() {
//		try {
//			businessMonitoredService.throwUnknownBusinessException();
//		} catch (Exception e) {
//			ErrorResponse handleException = exceptionHandlerDefault.handleException(e);
//			logger.info("Unknown Business Exception with User Message Error Response : " + handleException);
//			Assert.assertNotEquals(null, handleException.getErrorMessage());
//			Assert.assertNotEquals(null, handleException.getErrorCode());
//			Assert.assertNotEquals(null, handleException.getUserMessage());
//		}
//	}
//}
