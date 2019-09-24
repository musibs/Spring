package io.codefountain.spring.mvc.court.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReservationFormController {

	//@ExceptionHandler(ReservationNotAvailableException.class)
	/*
	 * public String hanlde(ReservationNotAvailableException rnae){ return
	 * "reservationNotAvailable"; }
	 */
	
	/*
	 * @ExceptionHandler public String handleDefault(Exception e) { return "error";
	 * }
	 */
	
	@RequestMapping("/reservationForm")
	public String someRequest() {
		return "welcome";
		/* throw new ReservationNotAvailableException("Not Available"); */
	}
}
