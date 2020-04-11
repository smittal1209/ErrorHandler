package com.services.error.handler.services;

import com.services.error.handler.enums.KnownExceptionsEnum;
import com.services.error.handler.exceptions.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shashankmittal
 * @created 11/04/20
 */
public class DummyService {

    private final Logger logger = LoggerFactory.getLogger(DummyService.class);

    private String[] knownErrorCodes = {"K1", "K2", "K3", "K4", "K5"};

    private String[] unknownErrorCode = {"U1", "U2", "U3", "U4", "U5"};

    private DummyDao dummyDao = new DummyDao();

    public void throwExceptionWithDisplayMessage() {
        try {
            dummyDao.getEntityById("1");
        } catch (RuntimeException ex) {
            logger.info("Error while fetching entity");
            BusinessException businessException = new BusinessException(KnownExceptionsEnum.ENTITY_NOT_FOUND.getCode(),
                    KnownExceptionsEnum.ENTITY_NOT_FOUND.getMessage(),
                    KnownExceptionsEnum.ENTITY_NOT_FOUND.getDisplayMessage()
            );
            logger.info("Throwing Known Business Exception as : " + businessException);
            throw businessException;
        }
    }

    public void throwExceptionWithoutDisplayMessage() {
        try {
            dummyDao.getEntityById("1");
        } catch (RuntimeException ex) {
            logger.info("Error while fetching entity");
            BusinessException businessException = new BusinessException(KnownExceptionsEnum.ENTITY_NOT_FOUND.getCode(),
                    KnownExceptionsEnum.ENTITY_NOT_FOUND.getMessage()
            );
            logger.info("Throwing Known Business Exception as : " + businessException);
            throw businessException;
        }
    }

    public void throwUnknownExceptionWithoutDisplayMessage() {
        try {
            dummyDao.getEntityById("1");
        } catch (RuntimeException ex) {
            logger.info("Error while fetching entity");
            BusinessException businessException = new BusinessException("404", "Record Not Found");
            logger.info("Throwing Known Business Exception as : " + businessException);
            throw businessException;
        }
    }

}
