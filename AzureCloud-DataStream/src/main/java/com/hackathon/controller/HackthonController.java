package com.hackathon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hackathon.dao.UserRepository;
import com.hackathon.domain.ItemDomain;


@RestController
@RequestMapping("api/v1")
public class HackthonController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users")
	public List<ItemDomain> getAllUsers() {

		return userRepository.findAll();

	}
	
	
	@GetMapping("/users/{id}")
	public ItemDomain retrieveStudent(@PathVariable long id) throws Exception {
		Optional<ItemDomain> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new Exception("User:"+id+":not present in Database");

		return user.get();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteStudent(@PathVariable long id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping("/user")
	public ResponseEntity<Object> createStudent(@RequestBody ItemDomain user) {
		ItemDomain item = userRepository.save(user);

		/*
		 * URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		 * .buildAndExpand(item.getItemid()).toUri();
		 */

		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(item.getItemid()).toUri()).build();

	}
	
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody ItemDomain user, @PathVariable long id) {

		Optional<ItemDomain> studentOptional = userRepository.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setItemid(id);
		
		userRepository.save(user);

		return ResponseEntity.noContent().build();
	}

}
