package io.codefountain.spring.mvc.service;

import java.util.List;

import io.codefountain.spring.mvc.domain.Reservation;
import io.codefountain.spring.mvc.exceptions.ReservationNotAvailableException;

public interface ReservationService {
	
	public List<Reservation> query(String courtName);
	public void addNewReservation(Reservation reservation) throws ReservationNotAvailableException;

}
