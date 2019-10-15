package io.codefountain.spring.mvc.service;

import java.util.List;

import io.codefountain.spring.mvc.domain.Reservation;

public interface ReservationService {
	
	public List<Reservation> query(String courtName);

}
