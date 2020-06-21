package com.spring.demo.rest.webservices.restfulwebservices.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.demo.rest.webservices.restfulwebservices.UserManagement.UserJpaRepository;
import com.spring.demo.rest.webservices.restfulwebservices.UserManagement.UserRecord;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	@Autowired
	UserJpaRepository userJpaRepository;
	
  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
		/*
		 * inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
		 * "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e",
		 * "ROLE_USER_2")); inMemoryUserList.add(new JwtUserDetails(2L, "admin",
		 * "$2a$10$UkMqr1MKvxJKKvspwc2taut.Hw4jecKFd6NCOck06sOb8oPTCJs2W",
		 * "ROLE_USER_2"));
		 */
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  
	  List<UserRecord> userRecord= userJpaRepository.findAll();
	  
	  for(UserRecord user : userRecord ) {
		  inMemoryUserList.add(new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(), "ROLE_USER_2"));
	  }
	  
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}


