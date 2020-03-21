package com.services.error.handler.generators;

import com.services.error.handler.constants.DefaultExceptionCodesEnum;
import com.services.error.handler.exceptions.BaseException;
import com.services.error.handler.exceptions.BusinessException;
import com.services.error.handler.mappers.DefaultErrorMapper;
import com.services.error.handler.mappers.IErrorMapper;
import com.services.error.handler.responses.ErrorResponse;
import com.services.error.handler.utils.Utility;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public class DefaultResponseGenerator implements IResponseGenerator<Object> {

    private IErrorMapper<String, String> errorMapper;

    public DefaultResponseGenerator() {
        this.errorMapper = new DefaultErrorMapper();
    }

    public DefaultResponseGenerator(IErrorMapper<String, String> errorMapper) {
        this.errorMapper = errorMapper;
    }

    @Override
    public ErrorResponse<Object> generate(BaseException baseException) {

        ErrorResponse<Object> errorResponse;

        String errorCode = baseException.getBaseError().getErrorCode() != null
                ? baseException.getBaseError().getErrorCode()
                : DefaultExceptionCodesEnum.DEFAULT_BASE_EXCEPTION.getErrorCode();

        String errorMessage = baseException.getBaseError().getErrorMessage() != null
                ? baseException.getBaseError().getErrorMessage()
                : DefaultExceptionCodesEnum.DEFAULT_BASE_EXCEPTION.getErrorMessage();

        String displayMessage = getDisplayMessage(baseException);

        errorResponse = new ErrorResponse<>(errorCode, errorMessage, displayMessage,
                baseException.getBaseError().getMetadata());

        return errorResponse;
    }

    private String getDisplayMessage(BaseException baseException) {
        String defaultUserMessage;

        String displayMessage = baseException.getBaseError().getDisplayMessage();

        String errorCode = baseException.getBaseError().getErrorCode();

        defaultUserMessage = baseException instanceof BusinessException
                ? DefaultExceptionCodesEnum.DEFAULT_BUSINESS_EXCEPTION.getErrorMessage()
                : DefaultExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorMessage();

        if (Utility.isNullOrBlank(displayMessage)) {
            displayMessage = errorMapper.getErrorMapping(errorCode) != null ? errorMapper.getErrorMapping(errorCode)
                    : defaultUserMessage;
        }
        return displayMessage;
    }
}
