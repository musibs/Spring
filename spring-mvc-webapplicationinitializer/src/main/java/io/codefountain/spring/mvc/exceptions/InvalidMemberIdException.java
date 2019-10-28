package io.codefountain.spring.mvc.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidMemberIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;

}
