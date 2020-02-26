package com.services.error.handler.exceptions;

/**
 * @param <T>
 * @author shashankmittal
 * @created 25/02/20
 */
public interface IBaseError<T> {
    String getErrorCode();

    String getErrorMessage();

    String getUserMessage();

    T getMetadata();
}
