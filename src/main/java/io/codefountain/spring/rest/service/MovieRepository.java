package io.codefountain.spring.rest.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import io.codefountain.spring.rest.pojo.Movie;

@Repository
public class MovieRepository {

	private final Map<Integer, Movie> movies = new HashMap<>();
	
	public MovieRepository() {
		movies.put(1, new Movie(1, "Chak De India!"));
		movies.put(2, new Movie(2, "Article 15"));
		movies.put(3, new Movie(3, "Tallash"));
		movies.put(4, new Movie(4, "Dangal"));
		movies.put(5, new Movie(5, "Titanic"));
		movies.put(6, new Movie(6, "Jab we Met"));
	}
	
	public Movie findById(int id) {
		return movies.get(id);
	}
	
	public Collection<Movie> findAll(){
		return movies.values();
	}
	
	public void delete(int id) {
		movies.remove(id);
	}
	
	public void create(Movie movie) {
		movies.put(movie.getId(), movie);
	}
	
	public void update(Movie movie) {
		if(movies.containsKey(movie.getId())) {
			movies.put(movie.getId(), movie);
		}
	}
	
}
