package io.codefountain.spring.mvc.court.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import io.codefountain.spring.mvc.court.domain.Player;
import io.codefountain.spring.mvc.court.domain.Reservation;
import io.codefountain.spring.mvc.court.domain.SportType;

@Component
public class DefaultReservationService implements ReservationService {

    private static final SportType TENNIS = new SportType(1, "Tennis");
    private static final SportType SOCCER = new SportType(2, "Soccer");

    private final List<Reservation> reservations = new ArrayList<>();

    public DefaultReservationService(){
        reservations.add(new Reservation("Tennis #1", new Date(), 16,
                new Player("Roger", "N/A"), TENNIS));
        reservations.add((new Reservation("Tennis #2", new Date(), 20,
                new Player("James", "N/A"), SOCCER)));
    }

    @Override
    public List<Reservation> query(String courtName) {

        return this.reservations
                .stream()
                .filter(reservation -> Objects.equals(reservation.getCourtName(),courtName))
                .collect(Collectors.toList());
    }
}
