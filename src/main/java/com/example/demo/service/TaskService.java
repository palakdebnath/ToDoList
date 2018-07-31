package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.TaskStatus;
import com.example.demo.dao.TaskDao;
import com.example.demo.entity.Task;

@Service
public class TaskService {

	@Autowired
	private TaskDao taskDao;

	public Task createTask(Task task) {
		return taskDao.createTask(task);
	}
	
	public List<Task> getAllTask() {
		return taskDao.getAllTask();
	}
	
	public List<Task> getAllTaskByStatus(TaskStatus status) {
		return taskDao.getAllTaskByStatus(status);
	}	
}
