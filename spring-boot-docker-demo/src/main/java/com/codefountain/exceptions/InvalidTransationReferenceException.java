package com.codefountain.exceptions;

public class InvalidTransationReferenceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidTransationReferenceException(String errorMessage) {
		super(errorMessage);
	}

}
