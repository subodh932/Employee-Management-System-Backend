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
import com.project.employee_management_system.model.Contact;
import com.project.employee_management_system.repository.ContactRepository;
import com.project.employee_management_system.services.FileUploadService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class ContactController {

	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	public FileUploadService fileService;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/contact")
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	@GetMapping("/contact/search/{name}")
	public List<Contact> getContactByName(@PathVariable(value = "name") String contactName) {
			return contactRepository.serchUserByName(contactName);
	}
	
	@GetMapping("/contact/search-state/{state}")
	public List<Contact> serchUserByState(@PathVariable(value = "state") String contactState) {
			return contactRepository.serchUserByState(contactState);
	}
	
	
	@GetMapping("/contact/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable(value = "id") Long contactId)
			throws ResourceNotFoundException {
		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + contactId));
		return ResponseEntity.ok().body(contact);
	}

	@PostMapping("/contact")
	public Contact createContact(@Valid @RequestBody Contact contact) {
		System.out.print("I am here");
		System.out.print(contact);
		return contactRepository.save(contact);
	}
	
	@PutMapping("/contact/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable(value = "id") Long contactId,
			@Valid @RequestBody Contact contactDetails) throws ResourceNotFoundException {
		final Contact updatedContact = contactRepository.save(contactDetails);
		return ResponseEntity.ok(updatedContact);
	}

	@DeleteMapping("/contact/{id}")
	public Map<String, Boolean> deleteContact(@PathVariable(value = "id") Long contactId)
			throws ResourceNotFoundException {
		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + contactId));

		contactRepository.delete(contact);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
