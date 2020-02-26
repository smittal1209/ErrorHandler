package com.services.error.handler.task;

import com.services.error.handler.exceptions.BaseException;
import com.services.error.handler.handler.ErrorResponse;
import com.services.error.handler.mapper.IErrorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
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
