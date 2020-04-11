package com.services.error.handler.enums;

/**
 * @author shashankmittal
 * @created 11/04/20
 */
public enum KnownExceptionsEnum {
    ENTITY_NOT_FOUND("K1", "Entity Not found!", "Entity Not found!");

    private String code;
    private String message;
    private String displayMessage;

    KnownExceptionsEnum(String code, String message, String displayMessage) {
        this.code = code;
        this.message = message;
        this.displayMessage = displayMessage;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

}
