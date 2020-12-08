package com.hackathon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.domain.ItemDomain;
@Repository
public interface UserRepository extends JpaRepository<ItemDomain, Long> {
	
}
