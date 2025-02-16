package com.project.employee_management_system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.employee_management_system.exception.ResourceNotFoundException;
import com.project.employee_management_system.model.Feedback;
import com.project.employee_management_system.repository.FeedbackRepository;
import com.project.employee_management_system.services.FileUploadService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class FeedbackController {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	public FileUploadService fileService;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/feedback")
	public List<Feedback> getAllFeedbacks() {
		return feedbackRepository.findAll();
	}

	@GetMapping("/feedback/search/{name}")
	public List<Feedback> getFeedbackByName(@PathVariable(value = "name") String feedbackName) {
			return feedbackRepository.searchUserByName(feedbackName);
	}
	
	@GetMapping("/feedback/search-state/{state}")
	public List<Feedback> searchUserByState(@PathVariable(value = "state") String feedbackState) {
			return feedbackRepository.searchUserByState(feedbackState);
	}
	
	
	@GetMapping("/feedback/{id}")
	public ResponseEntity<Feedback> getFeedbackById(@PathVariable(value = "id") Long feedbackId)
			throws ResourceNotFoundException {
		Feedback feedback = feedbackRepository.findById(feedbackId)
				.orElseThrow(() -> new ResourceNotFoundException("Feedback not found for this id :: " + feedbackId));
		return ResponseEntity.ok().body(feedback);
	}

	@PostMapping("/feedback")
	public Feedback createFeedback(@Valid @RequestBody Feedback feedback) {
		System.out.print("I am here");
		System.out.print(feedback);
		return feedbackRepository.save(feedback);
	}
	
	@PutMapping("/feedback/{id}")
	public ResponseEntity<Feedback> updateFeedback(@PathVariable(value = "id") Long feedbackId,
			@Valid @RequestBody Feedback feedbackDetails) throws ResourceNotFoundException {
		final Feedback updatedFeedback = feedbackRepository.save(feedbackDetails);
		return ResponseEntity.ok(updatedFeedback);
	}

	@DeleteMapping("/feedback/{id}")
	public Map<String, Boolean> deleteFeedback(@PathVariable(value = "id") Long feedbackId)
			throws ResourceNotFoundException {
		Feedback feedback = feedbackRepository.findById(feedbackId)
				.orElseThrow(() -> new ResourceNotFoundException("Feedback not found for this id :: " + feedbackId));

		feedbackRepository.delete(feedback);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
