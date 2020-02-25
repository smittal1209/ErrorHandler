package com.freecharge.error.handler.handler;

import com.freecharge.error.handler.exceptions.BaseException;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Feb 1, 2019
 */
public interface IResponseGenerator {

	public ErrorResponse<Object> generate(BaseException baseException);
}
