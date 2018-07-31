package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.constants.TaskStatus;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.service.TaskService;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {
	
	@InjectMocks
	private TaskController taskController;
	
	@Mock
	private TaskService taskService;
	
	private List<Task> taskList;
	
	private User assignedUser1;
	
	private User assignedUser2;
	
	private Task task1;
	
	private Task task2;
	
	
	@Before
	public void setUp() {
		taskList = new ArrayList<>();
		
		assignedUser1 = new User();
		assignedUser1.setUserId(1L);
		assignedUser1.setUserName("palakdebnath");
		assignedUser1.setFirstName("Palak");
		assignedUser1.setLastName("Debnath");
		
		assignedUser2 = new User();
		assignedUser2.setUserId(2L);
		assignedUser2.setUserName("happy");
		assignedUser2.setFirstName("Happy");
		assignedUser2.setLastName("Debnath");
		
		task1 = new Task();
		task2 = new Task();
		
		task1.setTaskId(1L);
		task1.setTaskName("Reading");
		task1.setDescription("Reading Book");
		task1.setStatus(TaskStatus.IN_PROGRESS);
		task1.setAssignedUserId(assignedUser1);
		
		task2.setTaskId(2L);
		task2.setTaskName("Writing");
		task2.setDescription("Writing Notes");
		task2.setStatus(TaskStatus.COMPLETE);
		task2.setAssignedUserId(assignedUser2);
		
		taskList.add(task1);
		taskList.add(task2);
	}

	@Test
	public void testGetAllTask() {
		when(taskService.getAllTask()).thenReturn(taskList);
		
		List<Task> taskListReturned = taskController.getAllTask();
		assertNotNull(taskListReturned);
		assertEquals(2, taskListReturned.size());
	}
	
	@Test
	public void testGetAllTaskException() {
		when(taskService.getAllTask()).thenThrow(new RuntimeException("Runtime Mock Exception!"));
		try {
			taskController.getAllTask();
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}
	
	@Test
	public void testCreateTask() {
		when(taskService.createTask(Mockito.any())).thenReturn(task1);
		
		Task taskCreated = taskController.createTask(task1);
		assertNotNull(taskCreated);
		assertEquals(new Long(1L), taskCreated.getTaskId());
		assertEquals("Reading", taskCreated.getTaskName());
		assertEquals(TaskStatus.IN_PROGRESS, taskCreated.getStatus());
		assertEquals("palakdebnath", taskCreated.getAssignedUser().getUserName());
	}
	
	@Test
	public void testCreateTaskException() {
		when(taskService.createTask(Mockito.any())).thenThrow(new RuntimeException("Runtime Mock Exception!"));
		
		try {
			taskController.createTask(task1);
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}

	@Test
	public void testGetAllTaskByStatusInProgress() {
		List<Task> inProgressTaskList = new ArrayList<>();
		inProgressTaskList.add(task1);
		
		when(taskService.getAllTaskByStatus(Mockito.any())).thenReturn(inProgressTaskList);
		
		List<Task> taskListReturned = taskController.getAllTaskByStatus(TaskStatus.IN_PROGRESS);
		assertNotNull(taskListReturned);
		assertEquals(1, taskListReturned.size());
		assertEquals(TaskStatus.IN_PROGRESS, taskListReturned.get(0).getStatus());
	}
	
	@Test
	public void testGetAllTaskByStatusInProgressException() {
		List<Task> inProgressTaskList = new ArrayList<>();
		inProgressTaskList.add(task1);
		
		when(taskService.getAllTaskByStatus(Mockito.any())).thenThrow(new RuntimeException("Runtime Mock Exception!"));
		
		try {
			taskController.getAllTaskByStatus(TaskStatus.IN_PROGRESS);
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}
	
}
