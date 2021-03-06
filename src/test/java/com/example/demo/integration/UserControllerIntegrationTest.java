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
public class UserControllerIntegrationTest {

    @LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
    
	@Test 
	public void testGetAllUser() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<List<User>> response = restTemplate.exchange(
				createURLWithPort("/users"),
				HttpMethod.GET, 
				entity, 
				new ParameterizedTypeReference<List<User>>() {});

		List<User> users = response.getBody();
		Assert.assertNotNull(users);
	}
	

	@Test 
	public void testCreateUser() {
		Task t = new Task(5L, "task", "task description" , TaskStatus.IN_PROGRESS);
		Set<Task> taskSet =  new HashSet<>();
		taskSet.add(t);
		User user = new User(8L, "johndey", "John", "Dey", taskSet);
		
		HttpEntity<User> entity = new HttpEntity<User>(user, headers);

		ResponseEntity<User> response = restTemplate.exchange(
				createURLWithPort("/users"),
				HttpMethod.POST, 
				entity, 
				User.class);

		User actualUser = response.getBody();
		Assert.assertNotNull(actualUser);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

 
}
