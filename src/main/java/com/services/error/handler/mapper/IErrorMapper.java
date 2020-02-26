package com.services.error.handler.mapper;

/**
 * @param <T>
 * @param <R>
 * @author shashankmittal
 * @created 25/02/20
 */
public interface IErrorMapper<T, R> {
    public boolean isErrorKnown(T error);

    public R getErrorMapping(T error);

    public void addErrorMapping(T error, R mapping);
}
