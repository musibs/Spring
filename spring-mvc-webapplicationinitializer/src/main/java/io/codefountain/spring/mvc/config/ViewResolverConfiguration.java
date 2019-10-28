package io.codefountain.spring.mvc.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import io.codefountain.spring.mvc.views.RssFeedView;

@Configuration
public class ViewResolverConfiguration implements WebMvcConfigurer {
	
	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ContentNegotiationManager contentNegotiationManager;
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
		internalResourceViewResolver.setSuffix(".jsp");
		internalResourceViewResolver.setOrder(1);
		return internalResourceViewResolver;
	}
	
	//@Bean
	//@Order(1)
	public ResourceBundleViewResolver resourceBundleViewResolver() {
		ResourceBundleViewResolver resourceBundleViewResolver = new ResourceBundleViewResolver();
		// Default basename is views. 
		//resourceBundleViewResolver.setBasename("views");
		return resourceBundleViewResolver;
	}
	
	//@Bean
	public XmlViewResolver xmlViewResolver() {
		XmlViewResolver xmlViewResolver = new XmlViewResolver();
		xmlViewResolver.setLocation(resourceLoader.getResource("/WEB-INF/views.xml"));
		return xmlViewResolver;
	}
	
	//@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
		
		ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
		contentNegotiatingViewResolver.setOrder(1);
		contentNegotiatingViewResolver.setContentNegotiationManager(contentNegotiationManager);
		
		List<View> defaultViews = new ArrayList<View>();
		defaultViews.add(marshallingView());
		defaultViews.add(rssFeedView());
		defaultViews.add(mappingJackson2JsonView());
		
		contentNegotiatingViewResolver.setDefaultViews(defaultViews);
		
		return contentNegotiatingViewResolver;
		
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		HashMap<String, MediaType> mediaTypes = new HashMap<>();
		mediaTypes.put("json", MediaType.APPLICATION_JSON);
		mediaTypes.put("xml", MediaType.APPLICATION_XML);
		mediaTypes.put("rss", MediaType.APPLICATION_RSS_XML);
		configurer.mediaTypes(mediaTypes)
		//.ignoreAcceptHeader(true)
		.defaultContentType(MediaType.APPLICATION_JSON);
	}
	
	
	@Bean
	public RssFeedView rssFeedView() {
		return new RssFeedView();
	}
	
	@Bean
	public MappingJackson2JsonView mappingJackson2JsonView() {
		return new MappingJackson2JsonView();
	}
	
	@Bean
	public MarshallingView marshallingView() {
		MarshallingView marshallingView = new MarshallingView(jaxb2Marshaller());
		return marshallingView;
	}
	
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(io.codefountain.spring.mvc.domain.FeatureList.class);
		return jaxb2Marshaller;
	}
	
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		WebMvcConfigurer.super.configureHandlerExceptionResolvers(resolvers);
	}
}
