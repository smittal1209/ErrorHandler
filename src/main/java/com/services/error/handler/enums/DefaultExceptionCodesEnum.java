package com.services.error.handler.enums;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public enum DefaultExceptionCodesEnum {

    DEFAULT_BUSINESS_EXCEPTION("BE-400", "Something went wrong! Please try again later!"),
    DEFAULT_SYSTEM_EXCEPTION("SE-500", "Something went wrong! Please try again later!");

    private final String errorCode;
    private final String errorMessage;

    DefaultExceptionCodesEnum(String code, String message) {
        errorCode = code;
        errorMessage = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
