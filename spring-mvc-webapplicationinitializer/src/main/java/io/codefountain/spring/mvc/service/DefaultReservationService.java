package io.codefountain.spring.mvc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.codefountain.spring.mvc.domain.Player;
import io.codefountain.spring.mvc.domain.Reservation;
import io.codefountain.spring.mvc.domain.SportType;

@Service
public class DefaultReservationService implements ReservationService{
	
	private static final SportType CRICKET = new SportType("C001", "Cricket");
	private static final SportType SOCCER = new SportType("C001", "Cricket");
	private static final List<Reservation> reservations = new ArrayList<Reservation>();
	
	public DefaultReservationService() {
		reservations.add(new Reservation("Cricket#1", new Date(), 8, new Player("Virat", "N/A"), CRICKET));
		reservations.add(new Reservation("Soccer#1", new Date(), 8, new Player("Sunil", "N/A"), SOCCER));
	}
	

	@Override
	public List<Reservation> query(String courtName) {
		return reservations.stream().filter(reservation -> Objects.equals(reservation.getCourtName(),courtName)).collect(Collectors.toList());
	}

	
}
