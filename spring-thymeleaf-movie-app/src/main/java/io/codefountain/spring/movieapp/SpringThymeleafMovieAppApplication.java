package io.codefountain.spring.movieapp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.codefountain.spring.movieapp.domain.Actor;
import io.codefountain.spring.movieapp.domain.Movie;
import io.codefountain.spring.movieapp.repository.ActorRepository;
import io.codefountain.spring.movieapp.repository.MovieRepository;

@SpringBootApplication
public class SpringThymeleafMovieAppApplication implements CommandLineRunner{

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ActorRepository actorRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringThymeleafMovieAppApplication.class, args);
	}

	
	@Override
	public void run(String... args) {
		Actor khan1 = new Actor("Aamir", "Khan", "All"); 
		Actor khan2 = new Actor("Shahrukh", "Khan", "Romance"); 
		Actor khan3 = new Actor("Rajkumar", "Rao", "Comedy");
		Actor khan4 = new Actor("Akshay", "Kumar", "Action");
	
		actorRepository.save(khan1);
		actorRepository.save(khan2);
		actorRepository.save(khan3);
		actorRepository.save(khan4);
		
		List<Movie> movies = new LinkedList<>();
		movies.add(new Movie("Devdas", "YRF", "2009", Arrays.asList(khan1, khan2)));
		movies.add(new Movie("Kyon Ki", "XYZ", "1909", Arrays.asList(khan3, khan2)));
		movies.add(new Movie("Kuch Kuch Hota Hain", "Dharma", "1989", Arrays.asList(khan4, khan2)));
		movies.add(new Movie("Chak De", "YRF", "2001", Arrays.asList(khan1, khan4)));
		movies.add(new Movie("Gagan", "Divya Fims", "2019", Arrays.asList(khan1, khan3)));
		movies.add(new Movie("Rajkumar", "Hirani", "2019", Arrays.asList(khan2, khan4)));
		
		movies.forEach(movie -> movieRepository.save(movie));
	}
}
