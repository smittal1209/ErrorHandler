package com.freecharge.error.handler.mapper;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Jan 24, 2019
 * @param <T>
 * @param <R>
 */
public interface IErrorMapper<T, R> {
	public boolean isErrorKnown(T error);

	public R getErrorMapping(T error);

	public void addErrorMapping(T error, R mapping);
}
