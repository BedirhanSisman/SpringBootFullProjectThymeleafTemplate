package com.bedirhansisman.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bedirhansisman.springboot.thymeleafdemo.entity.Employee;
import com.bedirhansisman.springboot.thymeleafdemo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/list")
	public List<Employee> getAllEmployees() {
		return employeeService.findAll();
	}

	@GetMapping("/list/{employeeID}")
	public Employee getEmployee(@PathVariable int employeeID) {
		return employeeService.findById(employeeID);
	}

	@PostMapping("/list")
	public Employee addEmployee(@RequestBody Employee employee) {
		Employee tempEmployee = employee;
		employeeService.save(tempEmployee);
		return tempEmployee;
	}

	@PutMapping("/list")
	public Employee updateEmployee(@RequestBody Employee employee) {
		Employee tempEmployee = employee;
		employeeService.save(tempEmployee);
		return tempEmployee;
	}

	@DeleteMapping("/list/{employeeID}")
	public void deleteEmployee(@PathVariable int employeeID) {
		employeeService.deleteById(employeeID);
	}

}
