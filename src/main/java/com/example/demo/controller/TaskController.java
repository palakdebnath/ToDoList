package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.TaskStatus;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/tasks")
	public Task createTask(@RequestBody Task task) {
		return taskService.createTask(task);
	}
	
	@GetMapping("/tasks")
	public List<Task> getAllTask() {
		return taskService.getAllTask();
	}
	
	@GetMapping("/tasks/{status}")
	public List<Task> getAllTaskByStatus(@PathVariable("status") TaskStatus status) {
		return taskService.getAllTaskByStatus(status);
	}
	
}
