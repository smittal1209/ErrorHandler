package com.services.error.handler.task;

import com.services.error.handler.exceptions.BaseException;
import com.services.error.handler.handler.ErrorResponse;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public interface ITask {
    public void execute(ErrorResponse<?> errorResponse, BaseException e);
}
