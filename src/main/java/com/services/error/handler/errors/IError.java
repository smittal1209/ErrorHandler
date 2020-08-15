package com.services.error.handler.errors;

/**
 * This is the generic interface for providing a basic Contract for the flow of
 * Error Information in the system.
 *
 * @param <T> The type of Metadata that the error can contain.
 * @author shashankmittal
 * @created 25/02/20
 */
public interface IError<T> {

    /**
     * @return A code for the error to identify it.
     */
    String getErrorCode();

    /**
     * @return A message that can be used to debug the error.
     * A basic message of what is the cause of this error or what has happened.
     */
    String getErrorMessage();

    /**
     * @return A display message that can be used to present to the end user.
     */
    String getDisplayMessage();

    /**
     * @return Any extra information that is required to in the system to pass across different layers.
     */
    T getMetadata();
}
