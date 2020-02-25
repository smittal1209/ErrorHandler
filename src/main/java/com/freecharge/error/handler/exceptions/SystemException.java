package com.freecharge.error.handler.exceptions;

import lombok.Getter;
import lombok.ToString;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Jan 23, 2019
 */
@Getter
@ToString(callSuper = true)
public class SystemException extends BaseException {

	public SystemException(IBaseError<?> baseError, Throwable cause) {
		super(baseError, cause);
	}

	public SystemException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}

	public SystemException(String errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
	}

}
