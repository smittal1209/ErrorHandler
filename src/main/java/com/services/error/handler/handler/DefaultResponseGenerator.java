package com.services.error.handler.handler;

import com.services.error.handler.constants.ExceptionCodesEnum;
import com.services.error.handler.exceptions.BaseException;
import com.services.error.handler.exceptions.BusinessException;
import com.services.error.handler.mapper.IErrorMapper;
import com.services.error.handler.utils.Utility;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public class DefaultResponseGenerator implements IResponseGenerator {

    private IErrorMapper<String, String> errorMapper;

    public DefaultResponseGenerator(IErrorMapper<String, String> errorMapper) {
        this.errorMapper = errorMapper;
    }

    @Override
    public ErrorResponse<Object> generate(BaseException baseException) {
        ErrorResponse<Object> errorResponse = null;
        String errorCode = baseException.getBaseError().getErrorCode() != null
                ? baseException.getBaseError().getErrorCode()
                : ExceptionCodesEnum.DEFAULT_BASE_EXCEPTION.getErrorCode();
        String errorMessage = baseException.getBaseError().getErrorMessage() != null
                ? baseException.getBaseError().getErrorMessage()
                : ExceptionCodesEnum.DEFAULT_BASE_EXCEPTION.getErrorMessage();
        String userMessage = getUserMessage(baseException);
        errorResponse = new ErrorResponse<Object>(errorCode, errorMessage, userMessage,
                baseException.getBaseError().getMetadata());
        return errorResponse;
    }

    private String getUserMessage(BaseException baseException) {
        String userMessage = baseException.getBaseError().getUserMessage();
        String errorCode = baseException.getBaseError().getErrorCode();
        String defaultUserMessage = ExceptionCodesEnum.DEFAULT_BASE_EXCEPTION.getErrorMessage();
        defaultUserMessage = baseException instanceof BusinessException
                ? ExceptionCodesEnum.DEFAULT_BUSINESS_EXCEPTION.getErrorMessage()
                : ExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorMessage();
        if (Utility.isNullOrBlank(userMessage)) {
            userMessage = errorMapper.getErrorMapping(errorCode) != null ? errorMapper.getErrorMapping(errorCode)
                    : defaultUserMessage;
        }
        return userMessage;
    }
}
