package io.codefountain.spring.mvc.sci.main;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import io.codefountain.spring.mvc.sci.configuration.SampleConfiguration;

public class ApplicationServletContainerInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> classes, ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationContext.register(SampleConfiguration.class);
		
		DispatcherServlet dispatcherServlet = new DispatcherServlet(annotationConfigWebApplicationContext);
		
		ServletRegistration.Dynamic servletRegistrtaion = servletContext.addServlet("applicationServlet", dispatcherServlet);
		servletRegistrtaion.setLoadOnStartup(1);
		servletRegistrtaion.addMapping("/");
	}

}
