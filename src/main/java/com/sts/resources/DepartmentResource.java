package com.sts.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sts.model.Department;
import com.sts.repository.DepartmentRepository;
import com.sts.service.DepartmentService;

@RestController
@RequestMapping(value = "/angular")
public class DepartmentResource {
	@Autowired
	private DepartmentRepository departmentRepo;

	@Autowired
	private DepartmentService departmentService;

	Logger logger = LoggerFactory.getLogger(EmployeeResource.class);

	@GetMapping(value = "/departmentdetails", produces = { "application/json" })
	public List<Department> getDepartments() {
		logger.info("Getting alll the department details");
		return departmentRepo.findAll();
	}

	@PostMapping(value = "/departmentdetails", consumes = { "application/json" }, produces = { "application/json" })
	public Department createDepartment(@RequestBody Department department) {

		String dprtmnt = null;
		String departmentName = null;
		
		try {
			dprtmnt = department.getDepartmentName();
			logger.info("Validating the department name={}", dprtmnt);
			departmentName = departmentService.getDepartment(dprtmnt);
			department.setDepartmentName(departmentName);

		} catch (Exception e) {
			logger.error("Error while validation the departmentdetails ");
		}
		return departmentRepo.save(department);
	}
}
