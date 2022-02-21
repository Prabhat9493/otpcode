package com.Phone.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phone.model.Users;
@Repository
public interface UserRepository extends JpaRepository{
		
		Users findByUsername(String username);
	
	} 

 
