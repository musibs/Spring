package io.codefountain.spring.movieapp.glee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import io.codefountain.spring.movieapp.users.User;

public interface GleeRepository extends PagingAndSortingRepository<Glee, Long>, GleeRepositoryCustom{
	
	Page<Glee> findAllByUser(User user, Pageable pageable);

}
