package com.services.error.handler.generators;

import com.services.error.handler.exceptions.BaseException;
import com.services.error.handler.responses.ErrorResponse;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public interface IResponseGenerator<T> {

    ErrorResponse<T> generate(BaseException baseException);
}
