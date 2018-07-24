package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User createUser(User usr) {
		return userDao.createUser(usr);
	}
	
	public User updateUser(User newUser) {
		return userDao.updateUser(newUser);
	}
	
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}
	

	public void deleteById(Long usrId) {
		userDao.deleteById(usrId);
	}
	
}
