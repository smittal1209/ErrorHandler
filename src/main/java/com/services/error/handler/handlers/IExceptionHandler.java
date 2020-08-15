package com.services.error.handler.handlers;

import com.services.error.handler.responses.ErrorResponse;
import com.services.error.handler.tasks.ITask;

import java.util.List;

/**
 * This is the core of this whole framework.
 * It processes any object of class {@link Exception} to convert it to framework understandable object of class
 * {@link com.services.error.handler.exceptions.BaseException}.
 * Afterwards, generates the {@link ErrorResponse} to be presented to the end user.
 * <p>
 * Also processes any extra tasks that are required to be executed after exception processing
 * such as Error Reporting, Alerting, Metrics Recording, etc.
 *
 * @author shashankmittal
 * @created 25/02/20
 */
public interface IExceptionHandler {

    /**
     * @param e Object of class {@link Exception} or any of its subclass.
     * @return Final object of {@link ErrorResponse } to be presented to the end user.
     */
    ErrorResponse handleException(Exception e);

    /**
     * @param tasks A list of tasks that are required to be executed after exception processing.
     *              Do not call this method or set null in case no task is required.
     */
    void setTasks(List<ITask> tasks);
}
