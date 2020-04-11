package com.services.error.handler.exceptions;

import com.services.error.handler.errors.DefaultError;
import com.services.error.handler.errors.IError;

import java.util.Map;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public abstract class BaseException extends RuntimeException {

    static final long serialVersionUID = -7034897190745766939L;

    protected IError<?> baseError;

    public BaseException(IError<?> baseError) {
        this.baseError = baseError;
    }

    public BaseException(IError<?> baseError, Throwable t) {
        super(t);
        this.baseError = baseError;
    }

    public BaseException(String errorMessage) {
        super(errorMessage);
        baseError = new DefaultError<Map<String, String>>(errorMessage);
    }

    public BaseException(String errorCode, String errorMessage) {
        super(errorMessage);
        baseError = new DefaultError<Map<String, String>>(errorCode, errorMessage);
    }

    public BaseException(String errorMessage, Throwable t) {
        super(errorMessage, t);
        baseError = new DefaultError<Map<String, String>>(errorMessage);
    }

    public BaseException(String errorCode, String errorMessage, Throwable t) {
        super(errorMessage, t);
        baseError = new DefaultError<Map<String, String>>(errorCode, errorMessage);
    }

    public BaseException(String errorCode, String errorMessage, String displayMessage) {
        super(errorMessage);
        baseError = new DefaultError<Map<String, String>>(errorCode, errorMessage, displayMessage);
    }

    public IError<?> getBaseError() {
        return baseError;
    }

}
