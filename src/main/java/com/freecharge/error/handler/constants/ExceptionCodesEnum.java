package com.freecharge.error.handler.constants;

public enum ExceptionCodesEnum {

	DEFAULT_BASE_EXCEPTION("BE-100", "Some error occured at downstream system!"), DEFAULT_BUSINESS_EXCEPTION("BE-200",
			"Some error occured at downstream system!"), DEFAULT_KNOWN_SYSTEM_EXCEPTION("SE-300",
					"Something went wrong!"), DEFAULT_SYSTEM_EXCEPTION("SE-400", "Something went wrong!");

	private String errorCode;
	private String errorMessage;

	private ExceptionCodesEnum(String code, String message) {
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
