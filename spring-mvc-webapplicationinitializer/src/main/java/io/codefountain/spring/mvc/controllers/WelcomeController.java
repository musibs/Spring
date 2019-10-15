package io.codefountain.spring.mvc.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

	@GetMapping("/welcome")
	public ModelAndView hello() {
		ModelAndView modeAndView = new ModelAndView("welcome");
		modeAndView.addObject("today", new Date());
		return modeAndView;
	}
	
}
