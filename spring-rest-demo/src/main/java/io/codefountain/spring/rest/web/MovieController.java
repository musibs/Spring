package io.codefountain.spring.rest.web;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import io.codefountain.spring.rest.pojo.Movie;
import io.codefountain.spring.rest.pojo.Movies;
import io.codefountain.spring.rest.service.MovieService;

@RestController
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping("/find/{id}")
	public Movie getMovie(@PathVariable("id") int id) {
		Movie movie = movieService.findById(id);
		if(Objects.isNull(movie)){
			throw new IllegalArgumentException("NoSuchMovieError");
		}
		return movie;
	}
	
	@GetMapping(name = "/findAll", produces = MediaType.APPLICATION_XML_VALUE)
	public String getMovies(Model model) {
		Movies movies = new Movies();
		movies.addMovies(movieService.findAll());
		model.addAttribute("movies", movies);
		return "movietemplate";
	}
	
	@PostMapping("/create")
	public void create(Movie movie) {
		Preconditions.checkNotNull(movie);
		movieService.create(movie);
	}
	
	@PutMapping("/update")
	public void update(Movie movie) {
		Preconditions.checkNotNull(movie);
		movieService.update(movie);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		movieService.delete(id);
		return "Deleted succesfully";
	}
}
