package io.codefountain.spring.rest.web;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import io.codefountain.spring.rest.pojo.Movie;
import io.codefountain.spring.rest.pojo.Movies;

@Configuration
public class MovieRestConfiguration {

	@Bean
	public View movieTemplate() {
		return new MarshallingView(jaxb2Marshaller());
	}
	
	@Bean
	public Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(Movie.class, Movies.class);
		return jaxb2Marshaller;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		return new BeanNameViewResolver();
	}
}
