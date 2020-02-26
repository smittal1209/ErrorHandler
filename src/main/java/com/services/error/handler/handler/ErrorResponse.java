package com.services.error.handler.handler;

import lombok.Getter;
import lombok.ToString;

/**
 * @param <T>
 * @author shashankmittal
 * @created 25/02/20
 */
@Getter
@ToString
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

}
