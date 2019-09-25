package io.codefountain.spring.movieapp.users;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	Page<User> findByEmailContains(String email, Pageable pageable);
	Page<User> findAllByEmail(String email, Pageable pageable);
	Page<User> findAllByEmailContainsAndEmail(String email, String auth, Pageable pageable);
	Boolean existsByEmail(String email);

}
