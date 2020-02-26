package com.services.error.handler.handler;

import com.services.error.handler.exceptions.BaseException;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public interface IResponseGenerator {

    public ErrorResponse<Object> generate(BaseException baseException);
}
