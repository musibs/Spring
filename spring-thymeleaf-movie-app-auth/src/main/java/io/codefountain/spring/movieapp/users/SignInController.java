package io.codefountain.spring.movieapp.users;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/signin")
public class SignInController {

	private final UserRepository userRepository;
	private final PasswordEncoder passworcEncoder;
	
	public SignInController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passworcEncoder = passwordEncoder;
	}
	
	@PostMapping
	User signin(@RequestParam String email, @RequestParam String password) {
		User user = new User(null, email, passworcEncoder.encode(password), User.Role.USER, 0D, null);
		return userRepository.save(user);
	}
	
	
	@PostMapping("/validateEmail")
	Boolean validateEmail(@RequestParam String email) {
		return userRepository.existsByEmail(email);
	}
	
}
