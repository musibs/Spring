package io.codefountain.spring.boot.okta.client;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping("/securedPage")
	public String securedPage(Model model, Principal principal) {
		return "securedPage";
	}
	
	@RequestMapping("/")
	public String index(Model model, Principal principal) {
		return "index";
	}
}
