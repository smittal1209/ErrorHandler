package com.services.error.handler.errors;

/**
 * @param <T>
 * @author shashankmittal
 * @created 25/02/20
 */
public interface IError<T> {

    String getErrorCode();

    String getErrorMessage();

    String getDisplayMessage();

    T getMetadata();
}
