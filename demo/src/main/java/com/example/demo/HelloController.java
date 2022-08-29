package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
	
	@Value("${config.username}")
	private String username;
	
	private String dob;
	
	@GetMapping("/name")
	public String GetName() {
		return "Hello, " + username;
	}
	
	@GetMapping("/dob/save")
	public void SaveDOB(@RequestParam String dob) {
		System.out.println(dob);
		this.dob = dob;
	}
	
	@GetMapping("/dob")
	public String GetDOB() {
		return this.dob;
	}

}