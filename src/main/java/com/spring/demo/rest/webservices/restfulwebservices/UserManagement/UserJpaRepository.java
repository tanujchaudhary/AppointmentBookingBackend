package com.spring.demo.rest.webservices.restfulwebservices.UserManagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.demo.rest.webservices.restfulwebservices.todo.Todo;

@Repository
public interface UserJpaRepository extends JpaRepository<UserRecord, Long>  {

	@Query("SELECT password  FROM UserRecord")
    public List<String> getAllUserName();
}
