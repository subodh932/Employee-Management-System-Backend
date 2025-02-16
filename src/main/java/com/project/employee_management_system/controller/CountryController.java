package com.project.employee_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.employee_management_system.model.Country;
import com.project.employee_management_system.repository.CountryRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class CountryController {
	@Autowired
	private CountryRepository countryRepository;

	@GetMapping("/countries")
	public List<Country> getAllCountrys() {
		return countryRepository.findAll();
	}
}
