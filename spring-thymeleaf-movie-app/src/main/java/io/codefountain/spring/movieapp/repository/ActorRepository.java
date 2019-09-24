package io.codefountain.spring.movieapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.codefountain.spring.movieapp.domain.Actor;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Long>{

	public List<Actor> findByGenre(String genre);
}
