package com.services.error.handler.exceptions;

/**
 * @param <T>
 * @author shashankmittal
 * @created 25/02/20
 */
public interface IBaseError<T> {
    public String getErrorCode();

    public String getErrorMessage();

    public String getUserMessage();

    public T getMetadata();
}
