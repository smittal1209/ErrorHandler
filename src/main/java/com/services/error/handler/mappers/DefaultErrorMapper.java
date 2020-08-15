package com.services.error.handler.mappers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public class DefaultErrorMapper implements IErrorMapper<String, String> {

    private final Map<String, String> codeVsMessage;

    public DefaultErrorMapper(Map<String, String> codeVsMessage) {
        this.codeVsMessage = new ConcurrentHashMap<>(codeVsMessage);
    }

    public DefaultErrorMapper() {
        codeVsMessage = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isErrorKnown(String error) {
        return codeVsMessage.containsKey(error);
    }

    @Override
    public String getErrorMapping(String error) {
        if (error != null && error.length() > 0) {
            return codeVsMessage.get(error);
        }
        return null;
    }

    @Override
    public void addErrorMapping(String error, String mapping) {
        if (error != null && error.length() > 0) {
            codeVsMessage.put(error.trim(), mapping);
        }
    }

}
