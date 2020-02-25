package com.freecharge.error.handler.exceptions;

import lombok.ToString;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Jan 21, 2019
 * @param <T>
 */
@ToString
public class DefaultBaseError<T> implements IBaseError<T> {

	private String errorCode;
	private String userMessage;
	private String errorMessage;
	private T metaData;

	public DefaultBaseError(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public DefaultBaseError(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public DefaultBaseError(String errorCode, String userMessage, String errorMessage) {
		this.errorCode = errorCode;
		this.userMessage = userMessage;
		this.errorMessage = errorMessage;
	}

	public DefaultBaseError(String errrorCode, String errorMessage, String userMessage, T metadata) {
		this.errorCode = errrorCode;
		this.userMessage = userMessage;
		this.errorMessage = errorMessage;
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
	public String getUserMessage() {
		return this.userMessage;
	}

	@Override
	public T getMetadata() {
		return this.metaData;
	}

}
