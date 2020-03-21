package com.services.error.handler.errors;

/**
 * @param <T>
 * @author shashankmittal
 * @created 25/02/20
 */
public class DefaultError<T> implements IError<T> {

    private String errorCode;
    private String displayMessage;
    private String errorMessage;
    private T metaData;

    public DefaultError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public DefaultError(String errorCode, String errorMessage) {
        this(errorMessage);
        this.errorCode = errorCode;
    }

    public DefaultError(String errorCode, String errorMessage, String displayMessage) {
        this(errorCode, errorMessage);
        this.displayMessage = displayMessage;
    }

    public DefaultError(String errrorCode, String errorMessage, String displayMessage, T metadata) {
        this(errrorCode, errorMessage, displayMessage);
        this.metaData = metadata;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public String getDisplayMessage() {
        return this.displayMessage;
    }

    @Override
    public T getMetadata() {
        return this.metaData;
    }

}
