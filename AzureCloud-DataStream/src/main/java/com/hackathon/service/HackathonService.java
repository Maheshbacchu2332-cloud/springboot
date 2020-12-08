package com.hackathon.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hackathon.dao.UserRepository;
import com.hackathon.entity.ItemDomain;

@Service
public class HackathonService {
	
	@Autowired
	private UserRepository userRepository;

	public List<ItemDomain> getAllUsers() {

		return userRepository.findAll();

	}
	
	
	public ItemDomain retrieveStudent( long id) throws Exception {
		Optional<ItemDomain> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new Exception("User:"+id+":not present in Database");

		return user.get();
	}
	
	public void deleteStudent( long id) {
		userRepository.deleteById(id);
	}
	
	public ResponseEntity<Object> createStudent( ItemDomain user) {
		ItemDomain item = userRepository.save(user);

		/*
		 * URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		 * .buildAndExpand(item.getItemid()).toUri();
		 */

		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(item.getItemid()).toUri()).build();

	}
	
	
	public ResponseEntity<Object> updateStudent( ItemDomain user,  long id) {

		Optional<ItemDomain> studentOptional = userRepository.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setItemid(id);
		
		userRepository.save(user);

		return ResponseEntity.noContent().build();
	}
}
