package com.sts.service;

import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
	public String getDepartment(String dprtmnt) {
		if (dprtmnt.equalsIgnoreCase("Maintainance") || dprtmnt.equalsIgnoreCase("Delivery")
				|| dprtmnt.equalsIgnoreCase("Infrastructure") || dprtmnt.equalsIgnoreCase("HR")) {
			return dprtmnt;
		} else {
			return "Department name not valid";
		}

	}

}
