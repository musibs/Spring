package io.codefountain.spring.mvc.main;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import io.codefountain.spring.mvc.config.CourtConfiguration;

public class ApplicationServletContainerInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(CourtConfiguration.class);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
		
		ServletRegistration.Dynamic applicationServletRegistration = ctx.addServlet("applicationServlet", dispatcherServlet);
		applicationServletRegistration.setLoadOnStartup(1);
		applicationServletRegistration.addMapping("/");
		
	}

}
