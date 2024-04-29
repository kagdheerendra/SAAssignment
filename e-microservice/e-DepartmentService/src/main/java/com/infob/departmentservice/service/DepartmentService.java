package com.infob.departmentservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infob.departmentservice.entity.Department;

@Service
public interface DepartmentService {
   
	Department saveDepartment(Department d);
	List<Department> getAllDepartment();
	Department updateDepartment(Department d) throws Exception;
	String deleteDepartment(int id) throws Exception;
}
