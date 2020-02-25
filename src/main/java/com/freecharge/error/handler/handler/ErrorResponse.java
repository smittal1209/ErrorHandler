package com.freecharge.error.handler.handler;

import lombok.Getter;
import lombok.ToString;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Jan 21, 2019
 * @param <T>
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
