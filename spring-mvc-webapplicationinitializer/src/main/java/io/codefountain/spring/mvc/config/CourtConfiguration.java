package io.codefountain.spring.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.codefountain.spring.mvc.interceptors.MembersInterceptor;
import io.codefountain.spring.mvc.interceptors.ProcessingTimeInterceptor;

@Configuration
@ComponentScan("io.codefountain.spring.mvc")
@EnableWebMvc
public class CourtConfiguration implements WebMvcConfigurer {
	
	
	//@Bean
	public ProcessingTimeInterceptor processingTimeInterceptor() {
		return new ProcessingTimeInterceptor();
	}
	
	//@Bean
	public MembersInterceptor memberInterceptor() {
		return new MembersInterceptor();
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(processingTimeInterceptor());
		//registry.addInterceptor(memberInterceptor()).addPathPatterns("/members");
		
	}

}
