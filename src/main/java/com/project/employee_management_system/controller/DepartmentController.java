package com.project.employee_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.employee_management_system.model.Department;
import com.project.employee_management_system.repository.DepartmentRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping("/departments")
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}
}
