package com.freecharge.error.handler.task;

import com.freecharge.error.handler.exceptions.BaseException;
import com.freecharge.error.handler.handler.ErrorResponse;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Feb 1, 2019
 */
public interface ITask {
	public void execute(ErrorResponse<?> errorResponse, BaseException e);
}
