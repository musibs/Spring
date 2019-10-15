package io.codefountain.spring.mvc.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Reservation {

	private String courtName;
	private Date date;
	private int hour;
	private Player player;
	private SportType sportType;
}
