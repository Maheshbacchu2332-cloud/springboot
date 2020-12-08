package com.hackathon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.entity.ItemDomain;
@Repository(value="userRepo")
public interface UserRepository extends JpaRepository<ItemDomain, Long> {
	
}
