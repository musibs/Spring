package io.codefountain.spring.mvc.exceptions;

import io.codefountain.spring.mvc.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Member member;
	private String message;

}
