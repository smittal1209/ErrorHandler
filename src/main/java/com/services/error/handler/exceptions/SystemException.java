package com.services.error.handler.exceptions;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public class SystemException extends BaseException {

    public SystemException(IBaseError<?> baseError, Throwable cause) {
        super(baseError, cause);
    }

    public SystemException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public SystemException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, errorMessage, cause);
    }
}
