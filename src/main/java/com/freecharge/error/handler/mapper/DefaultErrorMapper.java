package com.freecharge.error.handler.mapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Jan 24, 2019
 */
public class DefaultErrorMapper implements IErrorMapper<String, String> {

	private Map<String, String> codeVsMessage;

	public DefaultErrorMapper(Map<String, String> codeVsMessage) {
		super();
		this.codeVsMessage = new ConcurrentHashMap<String, String>(codeVsMessage);
	}

	public DefaultErrorMapper() {
		super();
		codeVsMessage = new ConcurrentHashMap<String, String>();
	}

	@Override
	public boolean isErrorKnown(String error) {
		return codeVsMessage.containsKey(error);
	}

	@Override
	public String getErrorMapping(String error) {
		if (error != null && error.trim().length() >= 0) {
			return codeVsMessage.get(error);
		}
		return null;
	}

	@Override
	public void addErrorMapping(String error, String mapping) {
		if (error != null && error.trim().length() >= 0) {
			codeVsMessage.put(error, mapping);
		}
	}

}
