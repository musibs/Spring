package io.codefountain.spring.mvc.court.config;

import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import io.codefountain.spring.mvc.court.exceptions.ReservationNotAvailableException;

//@Configuration
public class ExceptionResolverConfiguration /* implements WebMvcConfigurer */{
	
	//@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		resolvers.add(handlerExceptionResolver());
	}
	
	//@Bean
	public HandlerExceptionResolver handlerExceptionResolver() {
		Properties exceptionMappings = new Properties();
		exceptionMappings.setProperty(ReservationNotAvailableException.class.getName(), "rservatioNotFound");
		
		SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
		simpleMappingExceptionResolver.setExceptionMappings(exceptionMappings);
		//simpleMappingExceptionResolver.setDefaultErrorView("error");
		return simpleMappingExceptionResolver;
	}
	
	

}
