package io.codefountain.spring.mvc.court.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.codefountain.spring.mvc.court.domain.Employee;

@Controller
public class EmployeeController {

	
	 
	
	private List<Employee> loadEmployees() {
		final List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(1, "Somnath", "Developer"));
		employees.add(new Employee(2, "John", "Architect"));
		employees.add(new Employee(3, "Doe", "Designer"));
		employees.add(new Employee(4, "Jonathon", "Manager"));
		employees.add(new Employee(5, "Hill", "Tester"));
		return employees;
	}
	
	@GetMapping("/employees")
	public String viewEmployee(Model model ) {
		loadEmployees();
		model.addAttribute("employees", loadEmployees());
		return "employees";
	}
}
