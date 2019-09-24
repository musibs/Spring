package io.codefountain.spring.mvc.court.service;

import io.codefountain.spring.mvc.court.domain.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> query(String courtName);
}
