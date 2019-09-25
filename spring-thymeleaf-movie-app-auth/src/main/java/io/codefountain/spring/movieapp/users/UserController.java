package io.codefountain.spring.movieapp.users;

import java.util.HashSet;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.codefountain.spring.movieapp.errors.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
@Validated
public class UserController {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping
	Page<User> all(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable, OAuth2Authentication authentication){
		String auth = (String) authentication.getUserAuthentication().getPrincipal();
		String role = (String) authentication.getAuthorities().iterator().next().getAuthority();
		
		if(User.Role.USER.name().equals(role)) {
			return userRepository.findAllByEmail(auth, pageable);
		}
		
		return userRepository.findAll(pageable);
	}
	
	@GetMapping("/search")
	Page<User> search(@RequestParam String email, Pageable pageable, OAuth2Authentication authentication){
		String auth = (String) authentication.getUserAuthentication().getPrincipal();
		String role = (String) authentication.getAuthorities().iterator().next().getAuthority();
		
		if(User.Role.USER.name().equals(role)) {
			return userRepository.findAllByEmailContainsAndEmail(email, auth, pageable);
		}
		
		return userRepository.findByEmailContains(email, pageable);
	}
	
	@GetMapping("/findByEmail")
	@PreAuthorize("!hasAuthority('USER') || (authentication.principal == #email)")
	User findByEmail(String email, OAuth2Authentication authentication) {
		return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(User.class, "email", email));
	}
	
	@GetMapping("/{id}")
	@PostAuthorize("!hasAuthority('USER') || (returnObject != null && returnObject.email == authentication.principle)")
	User one(@PathVariable Long id) {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, "id", id.toString()));
	}
	
	
	@PutMapping("/{id}")
	@PreAuthorize("!hasAuthority('USER') || (authentication.principal == @userRepository.findById(#id).orElse(new io.codefountain.spring.movieapp.users.User()).email)")
	void update(@PathVariable Long id, @Valid @RequestBody User user) {
		User localUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, "id", id.toString()));
		user.setPassword(localUser.getPassword());
		user.setGlee(localUser.getGlee());
		userRepository.save(user);
	} 
	
	@PostMapping
	@PreAuthorize("!hasAuthority('USER')")
	User create(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("!hasAuthority('USER')")
	void delete(@PathVariable Long id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
		}
		else {
			throw new EntityNotFoundException(User.class, "id", id.toString());
		}
	}
	
	
	  @PutMapping("/{id}/changePassword") 
	  @PreAuthorize("!hasAuthority('USER') || (#oldPassword != null && !#oldPassword.isEmpty() && authentication.principal == @userRepository.findById(#id).orElse(new io.codefountain.spring.movieapp.users.User()).email)") 
	  void update(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min=3) @RequestParam String newPassword) {
		  User localUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, "id", id.toString()));
		  if(oldPassword == null || oldPassword.isEmpty() || passwordEncoder.matches(oldPassword, localUser.getPassword())) {
			  localUser.setPassword(passwordEncoder.encode(newPassword));
			  userRepository.save(localUser);
		  }
		  else {
			  throw new ConstraintViolationException("old password doesn't match", new HashSet<>());
		  }
	  }
	 	
	
	
}
