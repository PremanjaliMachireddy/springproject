package com.sts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sts.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("Select MAX(empId) from Employee")
	public Long findMaxEmpId();

	Employee findByEmpId(Long empId);

}
