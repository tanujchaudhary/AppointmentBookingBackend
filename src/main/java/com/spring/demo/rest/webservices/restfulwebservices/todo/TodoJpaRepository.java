package com.spring.demo.rest.webservices.restfulwebservices.todo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoJpaRepository extends JpaRepository<Todo, Long> {

  List<Todo> findByUsername(String username);
  
  List<Todo> findByUsernameAndTargetDate(String username,Date date);
  
}
