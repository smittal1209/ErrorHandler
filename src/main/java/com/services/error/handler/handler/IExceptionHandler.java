package com.services.error.handler.handler;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public interface IExceptionHandler {
    ErrorResponse handleException(Exception e);
}
