package com.services.error.handler.factories;

import com.services.error.handler.generators.DefaultResponseGenerator;
import com.services.error.handler.generators.IResponseGenerator;
import com.services.error.handler.handlers.DefaultExceptionHandler;
import com.services.error.handler.handlers.IExceptionHandler;
import com.services.error.handler.mappers.DefaultErrorMapper;
import com.services.error.handler.mappers.IErrorMapper;
import com.services.error.handler.processors.DefaultExceptionProcessor;
import com.services.error.handler.processors.IExceptionProcessor;
import com.services.error.handler.services.BusinessMonitoredService;
import com.services.error.handler.services.SystemMonitoredService;
import com.services.error.handler.tasks.ExceptionAuditLoggerTask;
import com.services.error.handler.tasks.ITask;
import com.services.error.handler.utils.Utility;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author shashankmittal
 * @created 21/03/20
 */
public class ObjectFactory {

    public static SystemMonitoredService systemMonitoredService() {
        return new SystemMonitoredService();
    }

    public static BusinessMonitoredService businessMonitoredService() {
        return new BusinessMonitoredService();
    }

    public static IErrorMapper<String, String> errorMapper() {
        Map<String, String> errorMapping = null;
        IErrorMapper<String, String> errorMapper = null;
        try {
            URL fileUrl = ObjectFactory.class.getClassLoader().getResource("errorCodeMapper.properties");
            errorMapping = Utility.getErrorMapping(new File(fileUrl.getFile()));
            errorMapper = new DefaultErrorMapper(errorMapping);
        } catch (IOException e) {
            e.printStackTrace();
            errorMapper = new DefaultErrorMapper();
        }
        return errorMapper;
    }

    public static IResponseGenerator responseGeneratorWithMapper() {
        IResponseGenerator responseGenerator = new DefaultResponseGenerator(errorMapper());
        return responseGenerator;
    }

    public static IExceptionHandler exceptionHandlerDefault() {
        DefaultExceptionHandler defaultExceptionHandler = new DefaultExceptionHandler();
        defaultExceptionHandler.setTasks(getTasks());
        return defaultExceptionHandler;
    }

    public static IExceptionHandler exceptionHandlerWithPackageFilter() {
        IExceptionProcessor exceptionProcessor = new DefaultExceptionProcessor("");
        DefaultExceptionHandler defaultExceptionHandler = new DefaultExceptionHandler(exceptionProcessor,
                responseGeneratorWithMapper());
        defaultExceptionHandler.setTasks(getTasks());
        return defaultExceptionHandler;
    }

    public static List<ITask> getTasks() {
        ITask task = new ExceptionAuditLoggerTask();
        List<ITask> tasks = new ArrayList<>();
        tasks.add(task);
        return tasks;
    }
}