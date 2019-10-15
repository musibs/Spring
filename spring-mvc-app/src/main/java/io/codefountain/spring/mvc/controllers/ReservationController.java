package io.codefountain.spring.mvc.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.codefountain.spring.mvc.domain.Reservation;
import io.codefountain.spring.mvc.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@GetMapping("/reservationQuery")
	public void setupForm() {}
	
	@PostMapping("/reservationQuery")
	public ModelAndView processReservationQuery(@RequestParam("courtName") String courtName) {
		if(Objects.isNull(courtName)) {
			throw new IllegalArgumentException("Specify cort name");
		}
		
		List<Reservation> reservations = reservationService.query(courtName);
		ModelAndView modelAndView = new ModelAndView("reservationQuery");
		modelAndView.addObject("reservations", reservations);
		return modelAndView;
	}
}
