package io.codefountain.spring.mvc.dsi.main;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import io.codefountain.spring.mvc.dsi.configuration.SampleConfiguration;

public class ApplicationServletContainerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {SampleConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	

}
