package com.freecharge.error.handler.test;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.freecharge.error.handler.exceptions.BaseException;
import com.freecharge.error.handler.exceptions.BusinessException;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Jan 28, 2019
 */
public class BusinessMonitoredService {
	private final Logger logger = LogManager.getLogger(BusinessMonitoredService.class);

	private String[] knownErrorCodes = { "K1", "K2", "K3", "K4", "K5" };

	private String[] unknownErrorCode = { "U1", "U2", "U3", "U4", "U5" };

	public void throwKnownBusinessException() {
		Random random = new Random();
		String errorCode = knownErrorCodes[random.nextInt(5)];
		BaseException businessException = new BusinessException(errorCode, "Known Error From Downstream System!");
		logger.info("Throwing Known Business Exception as : " + businessException);
		throw businessException;
	}

	public void throwUnknownBusinessException() {
		Random random = new Random();
		String errorCode = unknownErrorCode[random.nextInt(5)];
		BaseException businessException = new BusinessException(errorCode, "Unknown Error From Downstream System!");
		logger.info("Throwing Unknown Business Exception as : " + businessException);
		throw businessException;
	}

}
