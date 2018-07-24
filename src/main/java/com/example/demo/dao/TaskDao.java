package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.TaskStatus;
import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskDao {

	@Autowired
	private TaskRepository taskRepo;

	public Task createUser(Task task) {
		return taskRepo.save(task);
	}
	
	public List<Task> getAllTask() {
		return taskRepo.findAll();
	}
	
	public List<Task> getAllTaskByStatus(TaskStatus status) {
		return taskRepo.getAllTaskByStatus(status);
	}
}
