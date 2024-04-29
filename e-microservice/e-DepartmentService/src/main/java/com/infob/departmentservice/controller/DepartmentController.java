package com.infob.departmentservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.infob.departmentservice.entity.Department;
import com.infob.departmentservice.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService dService;
	
	@GetMapping("/getAllDepartment")
	public ResponseEntity<List<Department>> getAllDepartment(){
		return new ResponseEntity<List<Department>>(dService.getAllDepartment(), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Department> save(@RequestBody Department d){
		return new ResponseEntity<Department>(dService.saveDepartment(d), HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Department> update(@RequestBody Department d) throws Exception{
		return new ResponseEntity<Department>(dService.updateDepartment(d), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> getAllDepartment(@PathVariable int id) throws Exception{
		Map<String, String> response  =  new HashMap();
		String msg = dService.deleteDepartment(id);
		response.put("message", msg);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
