package com.freecharge.error.handler.processor;

import com.freecharge.error.handler.exceptions.BaseException;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Jan 21, 2019
 */
public interface IExceptionProcessor {
	public BaseException processException(Exception e);
}
