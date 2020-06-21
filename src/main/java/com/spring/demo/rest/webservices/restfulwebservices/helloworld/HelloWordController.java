package com.spring.demo.rest.webservices.restfulwebservices.helloworld;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://appointment-booking.s3.amazonaws.com")
@RestController
public class HelloWordController {

	// GET
	// URI - /hello-word
	// method - "hello world"
	// @RequestMapping(method = RequestMethod.GET , path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello world";
	}

	// hello-world-bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World Change");
		//throw new RuntimeException("Some Error has happened! Contact support");
	}

	// hello-world-bean
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVarible(@PathVariable String name) {
		return new HelloWorldBean("Hello world Parameter " +  name);
	}
}
