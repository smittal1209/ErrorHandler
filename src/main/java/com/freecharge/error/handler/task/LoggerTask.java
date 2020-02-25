package com.freecharge.error.handler.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.freecharge.error.handler.exceptions.BaseException;
import com.freecharge.error.handler.handler.ErrorResponse;
import com.freecharge.error.handler.mapper.IErrorMapper;

public class LoggerTask implements ITask {

	private final Logger logger = LoggerFactory.getLogger(LoggerTask.class);

	private IErrorMapper<String, String> errorMapper;

	public LoggerTask(IErrorMapper<String, String> errorMapper) {
		this.errorMapper = errorMapper;
	}

	@Override
	public void execute(ErrorResponse<?> errorResponse, BaseException e) {

		String errorCode = e.getBaseError().getErrorCode();
		String logMessage = "ErrorCode : " + e.getBaseError().getErrorCode() + " ErrorMessage : "
				+ e.getBaseError().getErrorMessage() + " UserMessage : " + e.getBaseError().getUserMessage();
		if (errorCode != null && errorMapper.isErrorKnown(errorCode)) {
			logger.error(logMessage);
		} else {
			logger.error(logMessage, e.getCause());
		}
	}
}
