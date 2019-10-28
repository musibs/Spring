package io.codefountain.spring.mvc.sci.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
	
	@GetMapping("/welcome")
	public ModelAndView sayHello() {
		ModelAndView modelAndView =  new ModelAndView("hello");
		modelAndView.addObject("message", "Hello World");
		return modelAndView;
	}
	

}
