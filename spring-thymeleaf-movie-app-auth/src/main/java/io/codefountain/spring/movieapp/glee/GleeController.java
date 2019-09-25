package io.codefountain.spring.movieapp.glee;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
import io.codefountain.spring.movieapp.users.User;
import io.codefountain.spring.movieapp.users.UserRepository;

@RestController
@RequestMapping("/api/glee")
@Validated
public class GleeController {

	private final GleeRepository gleeRepository;
	private final UserRepository userRepository;
	
	public GleeController(GleeRepository gleeRepository, UserRepository userRepository) {
		this.gleeRepository = gleeRepository;
		this.userRepository = userRepository;
	}
	
	
	@GetMapping
	Page<Glee> all(Pageable pageable, OAuth2Authentication oAuth2Authentication){
		String auth = (String) oAuth2Authentication.getUserAuthentication().getPrincipal();
        String role = oAuth2Authentication.getAuthorities().iterator().next().getAuthority();
        if (role.equals(User.Role.USER.name())) {
            User user = userRepository.findByEmail(auth).orElseThrow(() -> new EntityNotFoundException(User.class, "email", auth));
            return gleeRepository.findAllByUser(user, pageable);
        }
        return gleeRepository.findAll(pageable);
	}
	
	@GetMapping("/search")
    Page<Glee> search(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(value = "fromDate", required = false) LocalDate fromDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(value = "toDate", required = false) LocalDate toDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
            @RequestParam(value = "fromTime", required = false) LocalTime fromTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
            @RequestParam(value = "toTime", required = false) LocalTime toTime,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "value", required = false) Double cal,
            @RequestParam(value = "userId", required = false) Long userId,
            Pageable pageable, OAuth2Authentication authentication) {
        String auth = (String) authentication.getUserAuthentication().getPrincipal();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if (role.equals(User.Role.USER.name())) {
            User user = userRepository.findByEmail(auth).orElseThrow(() -> new EntityNotFoundException(User.class, "email", auth));
            userId = user.getId();
            return gleeRepository.filter(fromDate, toDate, fromTime, toTime, text, cal, userId, pageable);
        }
        return gleeRepository.filter(fromDate, toDate, fromTime, toTime, text, cal, userId, pageable);
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN') || (returnObject.user == @userRepository.findByEmail(authentication.principal).get())")
    Glee one(@PathVariable Long id) {
        return gleeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, "id", id.toString()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || (@gleeRepository.findById(#id).orElse(new net.reliqs.gleeometer.glee.Glee()).user == @userRepository.findByEmail(authentication.principal).get())")
    void update(@PathVariable Long id, @Valid @RequestBody Glee res) {
        if (gleeRepository.existsById(id)) {
        	gleeRepository.save(res);
        } else {
            throw new EntityNotFoundException(User.class, "id", id.toString());
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') || (#res != null && #res.user.id == @userRepository.findByEmail(authentication.principal).get().id)")
    Glee create(@Valid @RequestBody Glee res) {
        return gleeRepository.save(res);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || (@gleeRepository.findById(#id).orElse(new net.reliqs.gleeometer.glee.Glee()).user == @userRepository.findByEmail(authentication.principal).get())")
    void delete(@PathVariable Long id) {
        if (gleeRepository.existsById(id)) {
        	gleeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(User.class, "id", id.toString());
        }
    }

}
