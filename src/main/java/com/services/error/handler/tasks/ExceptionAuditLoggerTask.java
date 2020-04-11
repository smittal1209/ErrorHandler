package com.services.error.handler.tasks;

import com.services.error.handler.constants.Constants;
import com.services.error.handler.exceptions.BaseException;
import com.services.error.handler.responses.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public class ExceptionAuditLoggerTask implements ITask {

    private final Logger logger = LoggerFactory.getLogger(Constants.EXCEPTION_AUDIT_LOGGER);

    @Override
    public void execute(ErrorResponse<?> errorResponse, BaseException e) {
        String logMessage = "ErrorCode : " +
                e.getBaseError().getErrorCode() +
                " ErrorMessage : " +
                e.getBaseError().getErrorMessage() +
                " DisplayMessage : " +
                e.getBaseError().getDisplayMessage();
        logger.error(logMessage);
    }
}
