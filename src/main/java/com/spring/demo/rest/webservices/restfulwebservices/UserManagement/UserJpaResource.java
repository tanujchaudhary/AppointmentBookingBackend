package com.spring.demo.rest.webservices.restfulwebservices.UserManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.rest.webservices.restfulwebservices.todo.Todo;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserJpaResource {

	@Autowired
	UserJpaRepository userJpaRepository;
	
	@GetMapping("/jpa/appointment")
	public List<String> getAllUserInfo(){
		
		return userJpaRepository.getAllUserName();
	}
	
	@PostMapping("/signup")
	public boolean createUserAccount(@RequestBody UserRecord userRecord) {
	    		
		//TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userRecord.setPassword(encoder.encode(userRecord.getPassword()));
		userJpaRepository.save(userRecord);
		return true;
	 
	}
    
}
