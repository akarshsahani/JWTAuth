package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
	
	@RequestMapping("/welcome")
	public String welcome() {
		String text ="this is private page";
		return text;
	}
	
	
	@RequestMapping("/getusers")
	public String getUsers() {
		
		
		
		
		
		
		
		
		
		
		
		return "{\"name\":\"Akash\"}";
	}

}
