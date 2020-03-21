package com.services.error.handler.handlers;

import com.services.error.handler.exceptions.BaseException;
import com.services.error.handler.generators.DefaultResponseGenerator;
import com.services.error.handler.generators.IResponseGenerator;
import com.services.error.handler.processors.DefaultExceptionProcessor;
import com.services.error.handler.processors.IExceptionProcessor;
import com.services.error.handler.responses.ErrorResponse;
import com.services.error.handler.tasks.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public class DefaultExceptionHandler implements IExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    private IExceptionProcessor exceptionProcessor;

    private IResponseGenerator responseGenerator;

    private List<ITask> tasks = new ArrayList<>();

    public DefaultExceptionHandler() {
        this.exceptionProcessor = new DefaultExceptionProcessor();
        this.responseGenerator = new DefaultResponseGenerator();
    }

    public DefaultExceptionHandler(IExceptionProcessor exceptionProcessor, IResponseGenerator responseGenerator) {
        this.exceptionProcessor = exceptionProcessor;
        this.responseGenerator = responseGenerator;
    }

    public void setTasks(List<ITask> tasks) {
        this.tasks = tasks == null ? new ArrayList<>() : tasks;
    }

    public void setResponseGenerator(IResponseGenerator responseGenerator) {
        this.responseGenerator = responseGenerator;
    }

    @Override
    public ErrorResponse<Object> handleException(Exception e) {
        BaseException baseException = exceptionProcessor.processException(e);

        ErrorResponse<Object> errorResponse = responseGenerator.generate(baseException);

        executeTasks(errorResponse, baseException);

        logger.debug("Returning ErrorResponse as {}", errorResponse);
        return errorResponse;
    }

    private void executeTasks(ErrorResponse<?> errorResponse, BaseException baseException) {
        for (ITask iTask : tasks) {
            logger.debug("Executing Task as {}", iTask.getClass().getSimpleName());
            iTask.execute(errorResponse, baseException);
        }
    }
}
