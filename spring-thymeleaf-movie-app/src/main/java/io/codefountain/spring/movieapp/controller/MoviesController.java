package io.codefountain.spring.movieapp.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.codefountain.spring.movieapp.domain.Actor;
import io.codefountain.spring.movieapp.domain.Movie;
import io.codefountain.spring.movieapp.repository.ActorRepository;
import io.codefountain.spring.movieapp.repository.MovieRepository;

@Controller
public class MoviesController {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ActorRepository actorRepository;
	
	@GetMapping("/movie/{id}")
	public String movie(@PathVariable Long id, Model model) {
		model.addAttribute("movie", movieRepository.findById(id).get());
		model.addAttribute("actors", actorRepository.findAll());
		return "movie";
	}
	
	@GetMapping("/movies")
	public String getAllMovies(Model model) {
		model.addAttribute("movies", movieRepository.findAll());
		return "movies";
	}
	
	@PostMapping("/movies")
	public String addMovie(@RequestParam String title, @RequestParam String productionHouse, @RequestParam String releaseYear, Model model) {
		Movie newMovie = new Movie();
		newMovie.setTitle(title);
		newMovie.setProductionHouse(productionHouse);
		newMovie.setReleaseYear(releaseYear);
		
		movieRepository.save(newMovie);
		model.addAttribute("movie", newMovie);
		model.addAttribute("actors", actorRepository.findAll());
		return "redirect:/movie/"+newMovie.getId();
	}
	
	@PostMapping("/movie/{id}/actors")
	public String addMovieActors(@PathVariable Long id, @RequestParam Long actorId, Model model) {
		Optional<Actor> actorOptional =  actorRepository.findById(actorId);
		Actor actor = null;
		
		if(actorOptional.isPresent()) {
			actor = actorOptional.get();
		}
		
		Optional<Movie> movieOptional = movieRepository.findById(id);
		Movie movie = null;
		
		if(movieOptional.isPresent()) {
			movie = movieOptional.get();
		}
		
		if(Objects.nonNull(movie)) {
			if(!movie.hasActed(actor)) {
				movie.getActors().add(actor);
			}
			movieRepository.save(movie);
			model.addAttribute("movie", movieRepository.findById(id).get());
			model.addAttribute("actors", actorRepository.findAll());
			return "redirect:/movie/"+movie.getId();
		}
		model.addAttribute("movies", movieRepository.findAll());
		return "redirect:/movies";
	}
}
