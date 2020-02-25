package com.freecharge.error.handler.exceptions;

import java.util.Map;

import lombok.Getter;
import lombok.ToString;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Jan 21, 2019
 */
@Getter
@ToString
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	protected IBaseError<?> baseError;

	public BaseException(IBaseError<?> baseError) {
		this.baseError = baseError;
	}

	public BaseException(String errorMessage, Throwable t) {
		super(errorMessage, t);
		baseError = new DefaultBaseError<Map<String, String>>(errorMessage);
	}

	public BaseException(String errorMessage) {
		super(errorMessage);
		baseError = new DefaultBaseError<Map<String, String>>(errorMessage);
	}

	public BaseException(String errorCode, String errorMessage) {
		super(errorMessage);
		baseError = new DefaultBaseError<Map<String, String>>(errorCode, errorMessage);
	}

	public BaseException(String errorCode, String errorMessage, Throwable t) {
		super(errorMessage, t);
		baseError = new DefaultBaseError<Map<String, String>>(errorCode, errorMessage);
	}

	public BaseException(String errorCode, String errorMessage, String userMessage) {
		super(errorMessage);
		baseError = new DefaultBaseError<Map<String, String>>(errorCode, userMessage, errorMessage);
	}

	public BaseException(IBaseError<?> baseError, Throwable t) {
		super(t);
		this.baseError = baseError;
	}

}
