package com.freecharge.error.handler.test;

import java.net.SocketTimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.freecharge.error.handler.exceptions.SystemException;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Jan 28, 2019
 */
public class SystemMonitoredService {

	private final Logger logger = LogManager.getLogger(SystemMonitoredService.class);

	public void throwNullPointerException() {
		try {
			String test = "Test String";
			Thread.sleep(1000);
			test = null;
			logger.info("Going to throw Null Pointer Exception");
			test.toString();
		} catch (InterruptedException e) {
		}
	}

	public void throwKnownSystemException() {
		logger.info("Going to throw SocketTimeoutException");
		throw new SystemException("Socket Timeout Exception", new SocketTimeoutException("Socket Timeout Exception"));
	}
}
