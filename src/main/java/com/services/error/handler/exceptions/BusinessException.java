package com.services.error.handler.exceptions;

import com.services.error.handler.errors.IError;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public class BusinessException extends BaseException {

    public BusinessException(IError<?> baseError) {
        super(baseError);
    }

    public BusinessException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public BusinessException(String errorCode, String errorMessage, String displayMessage) {
        super(errorCode, errorMessage, displayMessage);
    }
}
