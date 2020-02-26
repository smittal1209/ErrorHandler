package com.services.error.handler.exceptions;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
@Getter
@ToString(callSuper = true)
@Slf4j
public class SystemException extends BaseException {

    public SystemException(IBaseError<?> baseError, Throwable cause) {
        super(baseError, cause);
    }

    public SystemException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public SystemException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, errorMessage, cause);
    }

    public static void main(String[] args) {
        log.info("hello");
    }
}
