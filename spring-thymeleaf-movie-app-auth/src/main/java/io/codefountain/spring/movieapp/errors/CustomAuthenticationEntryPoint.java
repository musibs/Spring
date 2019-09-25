package io.codefountain.spring.movieapp.errors;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

	private final HttpMessageConverter<String> messageConverter;
	
	private final ObjectMapper mapper;
	
	
	public CustomAuthenticationEntryPoint(ObjectMapper mapper) {
		this.messageConverter = new StringHttpMessageConverter();
		this.mapper = mapper;
	}
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
		apiError.setMessage(authException.getMessage());
		apiError.setDebugMessage(authException.getMessage());
		
		ServletServerHttpResponse outputMessage = new ServletServerHttpResponse(response);
		outputMessage.setStatusCode(HttpStatus.UNAUTHORIZED);
		
		messageConverter.write(mapper.writeValueAsString(apiError), MediaType.APPLICATION_JSON, outputMessage);
	}

	
}
