package com.hackathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.entity.ItemDomain;
import com.hackathon.service.HackathonService;

@RestController
@Component
@RequestMapping(path = "/api/v1")
public class HackthonController {

	@Autowired
	private HackathonService service;

	@GetMapping(path = "/users")
	public List<ItemDomain> getAllUsers() {

		return service.getAllUsers();

	}

	@GetMapping("/users/{id}")
	public ItemDomain retrieveStudent(@PathVariable long id) throws Exception {

		return service.retrieveStudent(id);
	}

	@DeleteMapping("/users/{id}")
	public void deleteStudent(@PathVariable long id) {
		service.deleteStudent(id);
	}

	@PostMapping("/user")
	public ResponseEntity<Object> createStudent(@RequestBody ItemDomain user) {

		return service.createStudent(user);

	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody ItemDomain user, @PathVariable long id) {

		service.updateStudent(user, id);

		return ResponseEntity.noContent().build();
	}

}
