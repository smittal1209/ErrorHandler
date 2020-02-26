package com.services.error.handler.handler;

/**
 * @param <T>
 * @author shashankmittal
 * @created 25/02/20
 */
public class ErrorResponse<T> {
    private String errorCode;
    private String errorMessage;
    private String userMessage;
    private T metadata;

    public ErrorResponse(String errorCode, String errorMessage, String userMessage, T metadata) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.userMessage = userMessage;
        this.metadata = metadata;
    }

    public ErrorResponse(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorResponse(String errorCode, String errorMessage, T metadata) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
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

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public T getMetadata() {
        return metadata;
    }

    public void setMetadata(T metadata) {
        this.metadata = metadata;
    }
}
