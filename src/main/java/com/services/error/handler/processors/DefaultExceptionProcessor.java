package com.services.error.handler.processors;

import com.services.error.handler.constants.DefaultExceptionCodesEnum;
import com.services.error.handler.errors.DefaultError;
import com.services.error.handler.errors.IError;
import com.services.error.handler.exceptions.BaseException;
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

    private List<String> packageFilter;

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
        String errorMessage = processThrowable(e);
        IError<?> defaultBaseError;
        if (e instanceof BaseException) {
            logger.debug("BaseException Found :: [{}]", e.getClass().getName());
            baseException = (BaseException) e;
            if (baseException.getBaseError() != null) {
                return baseException;
            } else {
                logger.debug("Error not found in BaseException.");
                return new BaseException(DefaultExceptionCodesEnum.DEFAULT_BASE_EXCEPTION.getErrorCode(),
                        DefaultExceptionCodesEnum.DEFAULT_BASE_EXCEPTION.getErrorMessage());
            }
        } else {
            logger.debug("Non BaseException Found :: [{}]", e.getClass().getName());
            defaultBaseError = new DefaultError<Void>(DefaultExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorCode(),
                    DefaultExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorMessage(), errorMessage);
            baseException = new SystemException(defaultBaseError, e);
        }
        logger.debug("Processed BaseException : [{}]", baseException, e);
        return baseException;
    }

    private String processThrowable(Throwable t) {
        StringBuffer stringBuffer = new StringBuffer();
        StackTraceElement[] stackTrace = t.getStackTrace();
        stringBuffer.append(t);
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (isTraceFiltered(stackTraceElement)) {
                stringBuffer.append(", at [ ClassName : " + stackTraceElement.getClassName() + " at LineNumber : "
                        + stackTraceElement.getLineNumber() + " ]");
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
