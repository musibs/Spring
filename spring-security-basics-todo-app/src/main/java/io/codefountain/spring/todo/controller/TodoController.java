package io.codefountain.spring.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.codefountain.spring.todo.domain.Todo;
import io.codefountain.spring.todo.service.TodoRepository;

@Controller
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;
	
	@PostMapping("/error")
	public String error() {
		return "error";
	}
	
	@GetMapping
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/todoAll")
	public String todos(Model model) {
		model.addAttribute("todos", todoRepository.findAll());
		return "todos";
	}
	
	@PostMapping("/todoNew")
	public String add(@RequestParam String todoItem, @RequestParam String status, Model model) {
		Todo todo = new Todo();
		todo.setTodoItem(todoItem);
		todo.setCompleted(status);
		todoRepository.save(todo);
		model.addAttribute("todos", todoRepository.findAll());
		return "redirect:/todoAll";
	}
	
	@PostMapping("/todoDelete/{id}")
	public String delete(@PathVariable long id, Model model) {
		todoRepository.deleteById(id);
		model.addAttribute("todos", todoRepository.findAll());
		return "redirect:/todoAll";
	}
	
}
