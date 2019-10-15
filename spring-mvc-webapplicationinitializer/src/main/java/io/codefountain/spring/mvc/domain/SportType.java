package io.codefountain.spring.mvc.domain;

import lombok.Data;
import lombok.NonNull;

@Data
public class SportType {

	@NonNull
	private String id;
	
	@NonNull
	private String name;
}
