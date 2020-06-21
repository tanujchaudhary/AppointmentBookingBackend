package com.spring.demo.rest.webservices.restfulwebservices.todo;

import java.net.URI;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoJpaResource {
	
	
	//@Autowired	
	//private TodoHardcodedServcie todoService;
//	
	@Autowired	
	private TodoJpaRepository todoJpaRepository;
	
	
	@GetMapping("/jpa/users/{username}/getAllBooking")
	public List<Todo> getAllBookingAppointment(@PathVariable String username,
			 @RequestBody Todo todo) {
				List<Todo> userTodo = todoJpaRepository.findByUsername(todo.getUsername());
				return userTodo;
	}
	
	
	
	@PostMapping("/jpa/users/{username}/bookAppointment")
	public boolean bookAppointment(@PathVariable String username,
			 @RequestBody Todo todo) {
				List<Todo> userTodo = todoJpaRepository.findByUsername(todo.getUsername());
							
				for(Todo key : userTodo) {
					if(key.getStartTime()==todo.getStartTime() && key.getDescription().equals("Available")) {
						
						key.setDescription("Booked");
						key.setBookedBy(username);
						Todo todoUpdated = todoJpaRepository.save(key);
						System.out.println();
						return true;
					}
				}
				
				return false;
		
	}
	
	@GetMapping("/jpa/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){
		System.out.println("Request from username" + username);
		return todoJpaRepository.findByUsername(username);
	}

	@GetMapping("/jpa/users/{username}/todos/{id}")
	public Todo getTodos(@PathVariable String username,@PathVariable long id){
		return todoJpaRepository.findById(id).get();
	}
	
	@DeleteMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, 
			@PathVariable long id){
		
		todoJpaRepository.deleteById(id);		
		return ResponseEntity.noContent().build();
		
	}


	
	//Update
	@PutMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(
			@PathVariable String username,
			@PathVariable long id , @RequestBody Todo todo
			){
		todo.setUsername(username);
		todo.setDescription("Available");
		Todo todoUpdated = todoJpaRepository.save(todo);
		
		return new ResponseEntity<Todo>(todoUpdated,HttpStatus.OK);
		
	}
	
	//Create
	//Update
	@PostMapping("/jpa/users/{username}/todos")
	public ResponseEntity<Void> createTodo(
			@PathVariable String username,
		    @RequestBody Todo todo
			){
		todo.setUsername(username);
		todo.setDescription("Available");
		Todo createdTodo = todoJpaRepository.save(todo);
		///users/{username}/todos/{}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(createdTodo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	
	
}
	