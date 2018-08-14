package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/greeting")
	public String getHello() {
		return "Hello";
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User newUser) {
		return userService.updateUser(newUser);
	}
	
	@GetMapping("/users")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}
	
	@DeleteMapping("/users/{userId}")
	public String deleteById(@PathVariable("userId") Long usrId) {
		userService.deleteById(usrId);
		return "User deleted successfully!";
	}
}
