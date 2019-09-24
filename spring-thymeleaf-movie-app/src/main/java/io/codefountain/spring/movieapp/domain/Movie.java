package io.codefountain.spring.movieapp.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private String productionHouse;
	private String releaseYear;
	
	@ManyToMany
	private List<Actor> actors;
	
	public Movie(String title, String productionHouse, String releaseYear, List<Actor> actors) {
		super();
		this.title = title;
		this.productionHouse = productionHouse;
		this.releaseYear = releaseYear;
		this.actors = actors;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getProductionHouse() {
		return productionHouse;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public boolean hasActed(Actor actor) {
		Objects.nonNull(actor);
		for(Actor containActor : getActors()) {
			if(containActor.getId() == actor.getId()) {
				return true;
			}
		}
		return false;
	}
}
