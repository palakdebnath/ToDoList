package com.example.demo.integration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.ToDoListApplication;
import com.example.demo.constants.TaskStatus;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ToDoListApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerIntegrationTest {

    @LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
    
	@Test 
	public void testGetAllTask() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<List<Task>> response = restTemplate.exchange(
				createURLWithPort("/tasks"),
				HttpMethod.GET, 
				entity, 
				new ParameterizedTypeReference<List<Task>>() {});

		List<Task> tasks = response.getBody();
		Assert.assertNotNull(tasks);
	}
	

	@Test 
	public void testCreateUser() {
		Task t = new Task(5L, "sampleTask", "Sample task description" , TaskStatus.IN_PROGRESS);
		Set<Task> taskSet =  new HashSet<>();
		taskSet.add(t);
		User user = new User(8L, "johndey", "John", "Dey", taskSet);
		
		t.setAssignedUserId(user);
		
		HttpEntity<Task> entity = new HttpEntity<Task>(t, headers);

		ResponseEntity<Task> response = restTemplate.exchange(
				createURLWithPort("/tasks"),
				HttpMethod.POST, 
				entity, 
				Task.class);

		Task actualTask = response.getBody();
		Assert.assertNotNull(actualTask);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

 
}
