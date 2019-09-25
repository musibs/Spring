package io.codefountain.spring.movieapp.errors;


import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

import lombok.Data;

@Data
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use= JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class ApiError {
	
	private HttpStatus status;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;
	
	private String message;
	private String debugMessage;
	
	private List<ApiSubError> apiSubErrors;
	
	private ApiError() {
		timeStamp = LocalDateTime.now();
	}
	
	ApiError(HttpStatus status) {
		this();
		this.status = status;
	}
	
	ApiError(HttpStatus status, Throwable ex){
		this(status, "Unexpected Error", ex);
	}
	
	ApiError(HttpStatus status, String message, Throwable ex){
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}
	
	private void addSubError(ApiSubError subError) {
		if(Objects.isNull(apiSubErrors)) {
			apiSubErrors = new LinkedList<ApiSubError>();
		}
		apiSubErrors.add(subError);
	}
	
	private void addValidationError(String object, String field, Object rejectedValue, String message) {
		addSubError(new ApiValidationError(object, field, rejectedValue, message));
	}
	
	private void addValidationError(String object, String message) {
		addSubError(new ApiValidationError(object, message));
	}
	
	

}

abstract class ApiSubError{
	
}


class ApiValidationError extends ApiSubError{
	
	private String object; 
	private String field;
	private Object rejectedValue;
	private String message;
	
	public ApiValidationError(String object, String message) {
		this.object = object;
		this.message = message;
	}
	
	public ApiValidationError(String object, String field, Object rejectedValue, String message) {
		this.object = object;
		this.field = field;
		this.rejectedValue = rejectedValue;
		this.message = message;
	}
	
}
class LowerCaseClassNameResolver extends TypeIdResolverBase{

	@Override
	public String idFromValue(Object value) {
		return value.getClass().getSimpleName().toLowerCase();
	}

	@Override
	public String idFromValueAndType(Object value, Class<?> suggestedType) {
		return idFromValue(value);
	}

	@Override
	public Id getMechanism() {
		return JsonTypeInfo.Id.CUSTOM;
	}
	
}
