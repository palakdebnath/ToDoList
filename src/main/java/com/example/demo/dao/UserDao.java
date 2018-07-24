package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserDao {
	
	@Autowired
	private UserRepository userRepo;

	public User createUser(User usr) {
		return userRepo.save(usr);
	}
	
	public User updateUser(User newUser) {
		return userRepo.save(newUser);
	}
	
	public List<User> getAllUser() {
		return userRepo.findAll();
	}
	
	public void deleteById(Long usrId) {
		userRepo.deleteById(usrId);
	}
	
	
}
