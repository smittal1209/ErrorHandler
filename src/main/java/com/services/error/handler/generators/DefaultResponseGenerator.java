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

        String errorCode = getErrorCode(baseException);

        String errorMessage = getErrorMessage(baseException);

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

    private String getErrorCode(BaseException baseException) {
        if (baseException.getBaseError().getErrorCode() != null) {
            return baseException.getBaseError().getErrorCode();
        }
        return baseException instanceof BusinessException
                ? DefaultExceptionCodesEnum.DEFAULT_BUSINESS_EXCEPTION.getErrorCode()
                : DefaultExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorCode();

    }

    private String getErrorMessage(BaseException baseException) {
        if (baseException.getBaseError().getErrorMessage() != null) {
            return baseException.getBaseError().getErrorMessage();
        }
        return baseException instanceof BusinessException
                ? DefaultExceptionCodesEnum.DEFAULT_BUSINESS_EXCEPTION.getErrorMessage()
                : DefaultExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorMessage();

    }
}
