package com.services.error.handler.tasks;

import com.services.error.handler.exceptions.BaseException;
import com.services.error.handler.responses.ErrorResponse;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public interface ITask {
    void execute(ErrorResponse<?> errorResponse, BaseException e);
}
