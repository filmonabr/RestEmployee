package edu.mum.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.mum.domain.Employee;

@Controller
public class EmployeeController {
	@RequestMapping("/")
	public String index(@ModelAttribute("employee") Employee emp) {
		return "employee";
	}

	@PostMapping("/")
	@ResponseBody
	public Employee saveEmployee(@Valid @RequestBody Employee emp) {
		return emp;

	}
}
