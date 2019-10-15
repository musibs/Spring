package io.codefountain.spring.mvc.domain;

import lombok.Data;
import lombok.NonNull;

@Data
public class Player {
	@NonNull
	private String name;
	@NonNull
	private String phone;

}
