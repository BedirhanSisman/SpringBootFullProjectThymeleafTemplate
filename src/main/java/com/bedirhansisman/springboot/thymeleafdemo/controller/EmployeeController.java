package com.bedirhansisman.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bedirhansisman.springboot.thymeleafdemo.entity.Employee;
import com.bedirhansisman.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/list")
	public String listOfEmployees(Model model) {

		// get employees from db
		List<Employee> employeeList = employeeService.findAll();

		// add to the spring model
		model.addAttribute("listOfEmployees", employeeList);

		return "employees/list-employees"; // list-employees name is actually html file name that you want to show on
											// browser.
	}

	@GetMapping("/list/{employeeID}")
	public String listOfEmployee(Model model, @PathVariable int employeeID) {

		// get employees from db
		Employee employee = employeeService.findById(employeeID);

		// add to the spring model
		model.addAttribute("listOfEmployees", employee);

		return "employees/list-employees"; // list-employees name is actually html file name that you want to show on
											// browser.
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		// create model attribute to bind form data
		Employee employee = new Employee();

		model.addAttribute("employee", employee);

		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

		// save the employee
		employeeService.save(theEmployee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeID") int employeeID, Model model) {

		// get the employee from the service
		Employee employee = employeeService.findById(employeeID);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);

		// send over to our form
		return "/employees/employee-form";
	}

	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("employeeID") int employeeID) {

		employeeService.deleteById(employeeID);

		return "redirect:/employees/list";
	}

}
