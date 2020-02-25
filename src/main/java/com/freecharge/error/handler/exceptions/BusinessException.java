package com.freecharge.error.handler.exceptions;

import lombok.Getter;
import lombok.ToString;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Jan 21, 2019
 */
@Getter
@ToString(callSuper = true)
public class BusinessException extends BaseException {
	public BusinessException(IBaseError<?> baseError) {
		super(baseError);
	}

	public BusinessException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public BusinessException(String errorCode, String errorMessage, String userMessage) {
		super(errorCode, errorMessage, userMessage);
	}
}
