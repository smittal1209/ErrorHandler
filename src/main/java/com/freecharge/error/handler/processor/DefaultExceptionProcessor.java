package com.freecharge.error.handler.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.freecharge.error.handler.constants.ExceptionCodesEnum;
import com.freecharge.error.handler.exceptions.BaseException;
import com.freecharge.error.handler.exceptions.DefaultBaseError;
import com.freecharge.error.handler.exceptions.IBaseError;
import com.freecharge.error.handler.exceptions.SystemException;
import com.freecharge.error.handler.utils.Utility;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Jan 23, 2019
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
		BaseException baseException = null;
		String errorMessage = processThrowable(e);
		IBaseError<?> defaultBaseError = null;
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
