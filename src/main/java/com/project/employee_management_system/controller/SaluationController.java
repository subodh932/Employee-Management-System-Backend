package com.project.employee_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.employee_management_system.model.Saluation;
import com.project.employee_management_system.repository.SaluationRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class SaluationController {
	@Autowired
	private SaluationRepository saluationRepository;

	@GetMapping("/saluations")
	public List<Saluation> getAllSaluations() {
		return saluationRepository.findAll();
	}
}
