package io.codefountain.spring.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import io.codefountain.spring.mvc.domain.Player;
import io.codefountain.spring.mvc.domain.Reservation;
import io.codefountain.spring.mvc.service.ReservationService;

@Controller
@SessionAttributes("reservation")
@RequestMapping("/reservationForm")
public class ReservationFormController {

	@Autowired
	private ReservationService reservationService;
	
	@GetMapping
	public String setupForm(@RequestParam(required = false, value = "username") String userName, Model model) {
		
		Player player = new Player(userName, null);
		Reservation reservation = new Reservation();
		reservation.setPlayer(player);
		model.addAttribute("reservation", reservation);
		return "reservationForm";
	}
	
	@PostMapping
	public String submitForm(@ModelAttribute("reservation") Reservation reservation, 
			BindingResult bindingResult, SessionStatus sessionStatus) {
		System.out.println("Data is "+reservation);
		reservationService.addNewReservation(reservation);
		return "redirect:reservationSuccess";
	}

}
