package io.codefountain.spring.rest.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.codefountain.spring.rest.pojo.Movie;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	public Movie findById(int id) {
		return movieRepository.findById(id);
	}
	
	public Collection<Movie> findAll(){
		return movieRepository.findAll();
	}
	
	public void delete(int id) {
		movieRepository.delete(id);
	}
	
	public void create(Movie movie) {
		movieRepository.create(movie);
	}
	
	public void update(Movie movie) {
		movieRepository.update(movie);
	}
}
