package com.infob.departmentservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infob.departmentservice.entity.Department;
import com.infob.departmentservice.repo.DepartmentRepository;
import com.infob.departmentservice.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository dRepo;
	
	@Override
	public Department saveDepartment(Department d) {
		// TODO Auto-generated method stub
		System.out.println(d);
		return dRepo.save(d);
	}

	@Override
	public List<Department> getAllDepartment() {
		// TODO Auto-generated method stub
		return dRepo.findAllDepartmentWithchildren();
	}

	@Override
	public Department updateDepartment(Department d) throws Exception {
		// TODO Auto-generated method stub
		Department dpmt = dRepo.findById(d.getId()).orElseThrow(()-> new Exception("Resource not found"));
		return dRepo.save(dpmt);
	}

	@Transactional
	@Override
	public String deleteDepartment(int id) throws Exception {
		// TODO Auto-generated method stub
		Department dpmt = dRepo.findById(id).orElseThrow(()-> new Exception("Resource not found"));
		System.out.println(dpmt.toString());
		dRepo.deleteDepartmentById(id);
		dRepo.deleteById(id);
		return "Deleted Successfully";
	}

}
