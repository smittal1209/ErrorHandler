package com.services.error.handler.services;

import com.services.error.handler.exceptions.BaseException;
import com.services.error.handler.exceptions.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @author shashankmittal
 * @created 21/03/20
 */
public class BusinessMonitoredService {
    private final Logger logger = LoggerFactory.getLogger(BusinessMonitoredService.class);

    private String[] knownErrorCodes = {"K1", "K2", "K3", "K4", "K5"};

    private String[] unknownErrorCode = {"U1", "U2", "U3", "U4", "U5"};

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
