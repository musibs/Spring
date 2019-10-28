package io.codefountain.spring.mvc.config;

import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import io.codefountain.spring.mvc.exceptions.MemberAlreadyExistsException;
import io.codefountain.spring.mvc.exceptions.MemberNotFoundException;

@Configuration
public class ExceptionHandlerConfig {//implements WebMvcConfigurer {

	
	//@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
	
		Properties exceptionMappings = new Properties();
		exceptionMappings.setProperty(MemberNotFoundException.class.getName(), "memberNotFound");
		exceptionMappings.setProperty(MemberAlreadyExistsException.class.getName(), "memberAlreadyExists");
		
		SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
		simpleMappingExceptionResolver.setExceptionMappings(exceptionMappings);		
		//simpleMappingExceptionResolver.setDefaultErrorView("error");
		resolvers.add(simpleMappingExceptionResolver);
	}
	
	
}
