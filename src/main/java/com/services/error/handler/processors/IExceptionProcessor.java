package com.services.error.handler.processors;

import com.services.error.handler.exceptions.BaseException;

/**
 * A base processor to convert object of class {@link Exception} or any of its child class
 * into framework understandable object of any of child class of {@link BaseException}.
 *
 * @author shashankmittal
 * @created 25/02/20
 */
public interface IExceptionProcessor {

    /**
     * @param e Object of class {@link Exception} or any of its child class.
     * @return Object of child class of {@link BaseException}.
     */
    BaseException processException(Exception e);

}
