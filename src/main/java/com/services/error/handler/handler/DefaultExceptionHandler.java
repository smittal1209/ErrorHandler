package com.services.error.handler.handler;

import com.services.error.handler.exceptions.BaseException;
import com.services.error.handler.processor.DefaultExceptionProcessor;
import com.services.error.handler.processor.IExceptionProcessor;
import com.services.error.handler.task.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public class DefaultExceptionHandler implements IExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    private IExceptionProcessor exceptionProcessor;

    private List<ITask> tasks;

    public void setTasks(List<ITask> tasks) {
        this.tasks = tasks;
    }

    public void setResponseGenerator(IResponseGenerator responseGenerator) {
        this.responseGenerator = responseGenerator;
    }

    private IResponseGenerator responseGenerator;

    public DefaultExceptionHandler(IExceptionProcessor exceptionProcessor, IResponseGenerator responseGenerator) {
        super();
        this.exceptionProcessor = exceptionProcessor;
        this.responseGenerator = responseGenerator;
    }

    public DefaultExceptionHandler(IResponseGenerator responseGenerator) {
        super();
        exceptionProcessor = new DefaultExceptionProcessor();
        this.responseGenerator = responseGenerator;
    }

    @Override
    public ErrorResponse<Object> handleException(Exception e) {
        BaseException baseException = exceptionProcessor.processException(e);
        ErrorResponse<Object> errorResponse = responseGenerator.generate(baseException);
        logger.debug("Returning ErrorResponse as {}", errorResponse);
        executeTasks(errorResponse, baseException);
        return errorResponse;
    }

    private void executeTasks(ErrorResponse<?> errorResponse, BaseException baseException) {
        for (ITask iTask : tasks) {
            logger.debug("Executing Task as {}", iTask.getClass().getSimpleName());
            iTask.execute(errorResponse, baseException);
        }
    }
}
