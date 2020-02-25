package com.freecharge.error.handler.exceptions;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Jan 21, 2019
 * @param <T>
 */
public interface IBaseError<T> {
	public String getErrorCode();

	public String getErrorMessage();

	public String getUserMessage();

	public T getMetadata();
}
