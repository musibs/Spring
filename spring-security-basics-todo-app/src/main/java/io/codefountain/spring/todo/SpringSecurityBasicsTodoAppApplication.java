package io.codefountain.spring.todo;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.codefountain.spring.todo.domain.Todo;
import io.codefountain.spring.todo.service.TodoRepository;

@SpringBootApplication
public class SpringSecurityBasicsTodoAppApplication implements CommandLineRunner {

	@Autowired
	private TodoRepository todoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicsTodoAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Collection<Todo> todos = Arrays.asList(new Todo("Revise Java", "No"), new Todo("Learn Driving", "Yes"), new Todo("Read Novel", "No"), new Todo("Cook Dinner", "Yes"));
		todos.forEach(todoRepository::save);
		
	}

}
