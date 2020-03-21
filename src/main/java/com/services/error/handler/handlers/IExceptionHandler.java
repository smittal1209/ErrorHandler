package com.services.error.handler.handlers;

import com.services.error.handler.responses.ErrorResponse;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public interface IExceptionHandler {

    ErrorResponse handleException(Exception e);
}
