package com.services.error.handler.services;

import com.services.error.handler.exceptions.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketTimeoutException;

/**
 * @author shashankmittal
 * @created 21/03/20
 */
public class SystemMonitoredService {

    private final Logger logger = LoggerFactory.getLogger(SystemMonitoredService.class);

    public void throwNullPointerException() {
        try {
            String test;
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
