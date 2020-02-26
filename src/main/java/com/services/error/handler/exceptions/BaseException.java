package com.services.error.handler.exceptions;

import java.util.Map;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public class BaseException extends RuntimeException {

    static final long serialVersionUID = -7034897190745766939L;

    protected IBaseError<?> baseError;

    public BaseException(IBaseError<?> baseError) {
        this.baseError = baseError;
    }

    public BaseException(String errorMessage, Throwable t) {
        super(errorMessage, t);
        baseError = new DefaultBaseError<Map<String, String>>(errorMessage);
    }

    public BaseException(String errorMessage) {
        super(errorMessage);
        baseError = new DefaultBaseError<Map<String, String>>(errorMessage);
    }

    public BaseException(String errorCode, String errorMessage) {
        super(errorMessage);
        baseError = new DefaultBaseError<Map<String, String>>(errorCode, errorMessage);
    }

    public BaseException(String errorCode, String errorMessage, Throwable t) {
        super(errorMessage, t);
        baseError = new DefaultBaseError<Map<String, String>>(errorCode, errorMessage);
    }

    public BaseException(String errorCode, String errorMessage, String userMessage) {
        super(errorMessage);
        baseError = new DefaultBaseError<Map<String, String>>(errorCode, userMessage, errorMessage);
    }

    public BaseException(IBaseError<?> baseError, Throwable t) {
        super(t);
        this.baseError = baseError;
    }

    public IBaseError<?> getBaseError() {
        return baseError;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "baseError=" + baseError +
                '}';
    }
}
