package com.sts.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sts.model.Employee;
import com.sts.model.UserLoginForm;
import com.sts.repository.EmployeeRepository;
import com.sts.repository.LoginRepository;
import com.sts.service.EmployeeService;

@RestController
@RequestMapping(value = "/angular")
public class EmployeeResource {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private LoginRepository loginRepo;

	Logger logger = LoggerFactory.getLogger(EmployeeResource.class);

	@GetMapping(value = "/employeedetails", produces = { "application/json" })
	public List<Employee> getEmployeeDetails() {

		List<Employee> employeeList = null;

		try {

			logger.info("Getting all the employee details");
			employeeList = employeeRepo.findAll();
		} catch (Exception e) {
			logger.error("Error while getting employee details");
		}
		return employeeList;
	}

	@GetMapping(value = "employeedetails/{empId}", produces = { "application/json" })
	public Employee getEmployee(@PathVariable Long empId) {

		Employee employeedetails = new Employee();

		try {
			logger.info("Getting employee details with empId={}", empId);
			employeedetails = employeeService.findByEmpId(empId);
		} catch (Exception e) {
			logger.error("Error while getting employee details with empId={}", empId);
		}
		return employeedetails;
	}

	@PostMapping(value = "/employeedetails", consumes = { "application/json" }, produces = { "application/json" })
	public Employee createEmployee(@RequestBody Employee employee) {

		boolean active = false;
		Long employeeId = null;

		try {
			logger.info("Getting active state if the employee");
			active = employee.getIsActive();

			logger.info("Generating the empId for the employee");
			employeeId = employeeService.save(employee);
			boolean isActive = employeeService.activation(active);

			employee.setEmpId(employeeId);
			employee.setIsActive(isActive);
			logger.info("saving the employee details with empId={}", employeeId);
			employee = employeeRepo.save(employee);
		} catch (Exception e) {
			logger.error("Error while saving employee details with empId={}", employeeId);
		}

		return employee;
	}

	@PostMapping(value = "logindetails", consumes = { "application/json" }, produces = { "application/json" })
	public UserLoginForm storeLoginDetails(@RequestBody UserLoginForm loginform) {

		logger.info("Storing the login credentials in DB");
		return loginRepo.save(loginform);
	}

	@PostMapping(value = "/logincredentials", consumes = { "application/json" }, produces = { "application/json" })
	public int login(@RequestBody UserLoginForm loginform) {

		String username = null;
		String password = null;

		int count = 0;
		int number = 0;

		try {
			logger.info("validating username and password");
			username = loginform.getUsername();
			password = loginform.getPassword();
			count = employeeService.getCount(username, password);
			if (count == 1) {
				logger.info("credentials matched");
				number = 1;
			} else {
				logger.info("Credentials are unmacthed");
				number = 0;
			}

		} catch (Exception e) {
			logger.error("Error while validating the login details");
		}
		return number;
	}

}
