package io.codefountain.spring.movieapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String firstName;
	private String lastName;
	private String genre;
	
	public Actor(String firstName, String lastName, String genre) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.genre = genre;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGenre() {
		return genre;
	}
	
	
	
}
