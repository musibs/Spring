package io.codefountain.spring.mvc.court.controller;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.codefountain.spring.mvc.court.domain.Reservation;
import io.codefountain.spring.mvc.court.service.ReservationService;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {

	@Autowired
    private ReservationService reservationService;

    @GetMapping
    public void setupForm(){}

    @PostMapping
    public String submitForm(@RequestParam("courtName") String courtName, Model model){
    	//throw new ReservationNotAvailableException(courtName);
		
		  List<Reservation> reservations = Collections.emptyList();
		  if(Objects.nonNull(courtName)){ reservations =
		  reservationService.query(courtName); } model.addAttribute("reservations",
		  reservations); return "reservationQuery";
		 

    }
}
