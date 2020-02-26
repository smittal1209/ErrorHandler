package com.services.error.handler.processor;

import com.services.error.handler.constants.ExceptionCodesEnum;
import com.services.error.handler.exceptions.BaseException;
import com.services.error.handler.exceptions.DefaultBaseError;
import com.services.error.handler.exceptions.IBaseError;
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
        super();
        packageFilter = new ArrayList<String>();
        if (!Utility.isNullOrBlank(defaultPackage)) {
            packageFilter.add(defaultPackage);
        }
    }

    public DefaultExceptionProcessor(List<String> packageFilter) {
        super();
        this.packageFilter = packageFilter;
    }

    public DefaultExceptionProcessor() {
        packageFilter = new ArrayList<String>();
    }

    @Override
    public BaseException processException(Exception e) {
        BaseException baseException;
        String errorMessage = processThrowable(e);
        IBaseError<?> defaultBaseError;
        if (e instanceof BaseException) {
            logger.debug("Going to process Exception as BaseException");
            baseException = (BaseException) e;
            if (baseException.getBaseError() != null) {
                return baseException;
            } else {
                logger.debug("BaseError not found in BaseException.");
                return new BaseException(ExceptionCodesEnum.DEFAULT_BASE_EXCEPTION.getErrorCode(),
                        ExceptionCodesEnum.DEFAULT_BASE_EXCEPTION.getErrorMessage());
            }
        } else {
            logger.debug("Going to process Exception as Exception");
            defaultBaseError = new DefaultBaseError<Void>(ExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorCode(),
                    ExceptionCodesEnum.DEFAULT_SYSTEM_EXCEPTION.getErrorMessage(), errorMessage);
            baseException = new SystemException(defaultBaseError, e);
        }
        logger.debug("Processed BaseException : {}", baseException);
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
        if (packageFilter == null || !(packageFilter != null && packageFilter.size() > 0)) {
            return true;
        }
        for (String string : packageFilter) {
            return className.startsWith(string);
        }
        return false;
    }

}
