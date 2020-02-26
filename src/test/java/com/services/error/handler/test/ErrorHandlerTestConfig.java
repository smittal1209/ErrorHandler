//package com.services.error.handler.test;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import com.services.error.handler.handler.IResponseGenerator;
//import com.services.error.handler.processor.IExceptionProcessor;
//import com.services.error.handler.task.ITask;
//import com.services.error.handler.utils.Utility;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.services.error.handler.constants.Constants;
//import com.services.error.handler.handler.DefaultExceptionHandler;
//import com.services.error.handler.handler.DefaultResponseGenerator;
//import com.services.error.handler.handler.IExceptionHandler;
//import com.services.error.handler.mapper.DefaultErrorMapper;
//import com.services.error.handler.mapper.IErrorMapper;
//import com.services.error.handler.processor.DefaultExceptionProcessor;
//import com.services.error.handler.task.LoggerTask;
//
///**
// * @author fcsm18808 Shashank Mittal
// * @created Jan 28, 2019
// */
//@Configuration
//public class ErrorHandlerTestConfig {
//
//	@Bean
//	public SystemMonitoredService systemMonitoredService() {
//		return new SystemMonitoredService();
//	}
//
//	@Bean
//	BusinessMonitoredService businessMonitoredService() {
//		return new BusinessMonitoredService();
//	}
//
//	@Bean
//	IErrorMapper<String, String> errorMapper() {
//		Map<String, String> errorMapping = null;
//		IErrorMapper<String, String> errorMapper = null;
//		try {
//			URL fileUrl = getClass().getClassLoader().getResource(Constants.DEFAULT_MAPPER_FILE);
//			errorMapping = Utility.getErrorMapping(new File(fileUrl.getFile()));
//			errorMapper = new DefaultErrorMapper(errorMapping);
//		} catch (IOException e) {
//			e.printStackTrace();
//			errorMapper = new DefaultErrorMapper();
//		}
//		return errorMapper;
//	}
//
//	@Bean
//    IResponseGenerator responseGeneratorWithMapper() {
//		IResponseGenerator responseGenerator = new DefaultResponseGenerator(errorMapper());
//		return responseGenerator;
//	}
//
//	@Bean
//	IExceptionHandler exceptionHandlerDefault() {
//		DefaultExceptionHandler defaultExceptionHandler = new DefaultExceptionHandler(responseGeneratorWithMapper());
//		defaultExceptionHandler.setTasks(getTasks());
//		return defaultExceptionHandler;
//	}
//
//	@Bean
//	IExceptionHandler exceptionHandlerWithPackageFilter() {
//		IExceptionProcessor exceptionProcessor = new DefaultExceptionProcessor(Constants.COM_FREECHARGE);
//		DefaultExceptionHandler defaultExceptionHandler = new DefaultExceptionHandler(exceptionProcessor,
//				responseGeneratorWithMapper());
//		defaultExceptionHandler.setTasks(getTasks());
//		return defaultExceptionHandler;
//	}
//
//	private List<ITask> getTasks() {
//		ITask task = new LoggerTask(errorMapper());
//		List<ITask> tasks = new ArrayList<ITask>();
//		tasks.add(task);
//		return tasks;
//	}
//}
