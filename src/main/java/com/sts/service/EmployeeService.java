package com.sts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.model.Employee;
import com.sts.repository.EmployeeRepository;
import com.sts.repository.LoginRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private LoginRepository LoginRepo;

	public boolean activation(boolean active) {

		if (active != true && active != false) {
			active = false;
		}
		return active;
	}

	public Long save(Employee employee) {
		Long newEmpId = null;
		Long empId = null;

		try {
			empId = employeeRepo.findMaxEmpId();
			if (empId != null) {
				newEmpId = empId + 2;
			} else {
				newEmpId = (long) 1421;
			}
			employee.setEmpId(newEmpId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newEmpId;
	}

	public Employee findByEmpId(Long empId) {
		return employeeRepo.findByEmpId(empId);
	}

	public int getCount(String username, String password) {
		int count = 0;
		
		count = LoginRepo.getCount(username, password);
		return count;

	}

}
