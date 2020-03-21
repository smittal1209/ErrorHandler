package com.services.error.handler.responses;

/**
 * @param <T>
 * @author shashankmittal
 * @created 25/02/20
 */
public class ErrorResponse<T> {

    private String errorCode;
    private String errorMessage;
    private String displayMessage;
    private T metadata;

    public ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorResponse(String errorCode, String errorMessage, String displayMessage) {
        this(errorCode, errorMessage);
        this.displayMessage = displayMessage;
    }

    public ErrorResponse(String errorCode, String errorMessage, String displayMessage, T metadata) {
        this(errorCode, errorMessage, displayMessage);
        this.metadata = metadata;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public T getMetadata() {
        return metadata;
    }

    public void setMetadata(T metadata) {
        this.metadata = metadata;
    }
}
