package com.freecharge.error.handler.handler;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Jan 21, 2019
 */
public interface IExceptionHandler {
	public ErrorResponse handleException(Exception e);
}
