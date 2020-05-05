package com.example.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;

@RestController
@RequestMapping(value="/angular")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private DepartmentRepository departmentRepo;
	
	@GetMapping(value="/check")
	public String healthcheck() {
		return "working.......";
	}
	
	
	

}
