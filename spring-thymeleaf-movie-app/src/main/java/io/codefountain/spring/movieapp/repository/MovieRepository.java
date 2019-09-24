package io.codefountain.spring.movieapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.codefountain.spring.movieapp.domain.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>{

}
