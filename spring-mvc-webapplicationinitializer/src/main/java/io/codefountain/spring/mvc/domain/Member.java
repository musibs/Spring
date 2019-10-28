package io.codefountain.spring.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Member {

	private String memberId;
	private String firstName;
	private String lastName;
}
