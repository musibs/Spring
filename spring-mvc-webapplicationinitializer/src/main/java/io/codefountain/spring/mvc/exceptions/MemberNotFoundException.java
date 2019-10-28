package io.codefountain.spring.mvc.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String memberId;
	private String message;

}
