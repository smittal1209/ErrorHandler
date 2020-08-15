package com.services.error.handler.generators;

import com.services.error.handler.exceptions.BaseException;
import com.services.error.handler.responses.ErrorResponse;

/**
 * @param <T> The type of Metadata that the error can contain.
 * @author shashankmittal
 * @created 25/02/20
 */
public interface IResponseGenerator<T> {

    /**
     * Uses the information present in the object of the class {@link BaseException}
     * and generates a final {@link ErrorResponse} to be presented to the end user.
     *
     * @param baseException An object of the class {@link BaseException}.
     * @return An object of the class {@link ErrorResponse}.
     */
    ErrorResponse<T> generate(BaseException baseException);
}
