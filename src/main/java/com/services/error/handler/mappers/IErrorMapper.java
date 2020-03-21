package com.services.error.handler.mappers;

/**
 * @param <T>
 * @param <R>
 * @author shashankmittal
 * @created 25/02/20
 */
public interface IErrorMapper<T, R> {

    boolean isErrorKnown(T error);

    R getErrorMapping(T error);

    void addErrorMapping(T error, R mapping);
}
