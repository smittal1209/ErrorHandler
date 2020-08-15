package com.services.error.handler.processors;

import com.services.error.handler.enums.DefaultExceptionCodesEnum;
import com.services.error.handler.errors.DefaultError;
import com.services.error.handler.errors.IError;
import com.services.error.handler.exceptions.BaseException;
import com.services.error.handler.exceptions.BusinessException;
import com.services.error.handler.exceptions.SystemException;
import com.services.error.handler.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public class DefaultExceptionProcessor implements IExceptionProcessor {

    private final Logger logger = LoggerFactory.getLogger(DefaultExceptionProcessor.class);

    private final List<String> packageFilter;

    public DefaultExceptionProcessor(String defaultPackage) {
        packageFilter = new ArrayList<>();
        if (!Utility.isNullOrBlank(defaultPackage)) {
            packageFilter.add(defaultPackage);
        }
    }

    public DefaultExceptionProcessor(List<String> packageFilter) {
        this.packageFilter = packageFilter;
    }

    public DefaultExceptionProcessor() {
        packageFilter = new ArrayList<>();
    }

    @Override
    public BaseException processException(Exception e) {
        BaseException baseException;
        if (e instanceof BaseException) {
            logger.debug("BaseException Found :: [{}]", e.getClass().getName());
            baseException = (BaseException) e;
            if (baseException.getBaseError() != null) {
                return baseException;
            } else if (e instanceof BusinessException) {
                baseException = new BusinessException(DefaultExceptionCodesEnum.DEFAULT_BUSINESS_EXCEPTION.getErrorCode(),
                        DefaultExceptionCodesEnum.DEFAULT_BUSINESS_EXCEPTION.getErrorMessage());
            } else {
                baseException = getSystemException(e);
            }
        } else {
            logger.debug("Non BaseException Found :: [{}]", e.getClass().getName());
            baseException = getSystemException(e);
        }
        logger.debug("Processed BaseException : [{}]", baseException, e);
        return baseException;
    }

    private SystemException getSystemException(Exception e) {
        String errorMessage = processThrowable(e);
        IError<?> defaultBaseError = new DefaultError<Void>(DefaultExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorCode(),
                errorMessage, DefaultExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorMessage());
        return new SystemException(defaultBaseError, e);
    }

    private String processThrowable(Throwable t) {
        StringBuilder stringBuffer = new StringBuilder();
        StackTraceElement[] stackTrace = t.getStackTrace();
        stringBuffer.append(t);
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (isTraceFiltered(stackTraceElement)) {
                stringBuffer.append(", at [ ClassName : ")
                        .append(stackTraceElement.getClassName())
                        .append(" at Line : ")
                        .append(stackTraceElement.getLineNumber())
                        .append(" ]");
            }
        }
        return stringBuffer.toString();
    }

    private boolean isTraceFiltered(StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        if (packageFilter == null || packageFilter.size() == 0) {
            return true;
        }
        for (String string : packageFilter) {
            return className.startsWith(string);
        }
        return false;
    }

}
