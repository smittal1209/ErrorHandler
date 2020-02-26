package com.services.error.handler.processor;

import com.services.error.handler.exceptions.BaseException;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public interface IExceptionProcessor {
    BaseException processException(Exception e);
}
