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

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;

	private List<User> userList;
	
	private User user1;
	
	private User user2;
	
	@Before
	public void setUp() {
		userList = new ArrayList<>();
		
		user1 = new User();
		user1.setUserId(1L);
		user1.setUserName("palakdebnath");
		user1.setFirstName("Palak");
		user1.setLastName("Debnath");
		
		user2 = new User();
		user2.setUserId(2L);
		user2.setUserName("happy");
		user2.setFirstName("Happy");
		user2.setLastName("Debnath");
		
		userList.add(user1);
		userList.add(user2);
	}
	
	@Test
	public void testGetAllUser() {
		when(userService.getAllUser()).thenReturn(userList);
		
		List<User> userListReturned = userController.getAllUser();
		assertNotNull(userListReturned);
		assertEquals(2, userListReturned.size());
	}
	
	@Test
	public void testGetAllUserException() {
		when(userService.getAllUser()).thenThrow(new RuntimeException("Runtime Mock Exception!"));
		
		try {
			userController.getAllUser();
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}
	
	@Test
	public void testCreateUser() {
		when(userService.createUser(Mockito.any())).thenReturn(user1);
		
		User userCreated = userController.createUser(user1);
		assertNotNull(userCreated);
		assertEquals(new Long(1L), userCreated.getUserId());
		assertEquals("palakdebnath", userCreated.getUserName());
		assertEquals("Palak", userCreated.getFirstName());
		assertEquals("Debnath", userCreated.getLastName());
	}
	
	@Test
	public void testCreateUserException() {
		when(userService.createUser(Mockito.any())).thenThrow(new RuntimeException("Runtime Mock Exception!"));
		
		try {
			userController.createUser(user1);
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}
	
	@Test
	public void testUpdateUser() {
		
		user1.setUserName("palakdebnath111");
		when(userService.updateUser(Mockito.any())).thenReturn(user1);
		
		User userUpdated = userController.updateUser(user1);
		assertNotNull(userUpdated);
		assertEquals(new Long(1L), userUpdated.getUserId());
		assertEquals("palakdebnath111", userUpdated.getUserName());
		assertEquals("Palak", userUpdated.getFirstName());
		assertEquals("Debnath", userUpdated.getLastName());
	}
	
	@Test
	public void testUpdateUserException() {
		when(userService.updateUser(Mockito.any())).thenThrow(new RuntimeException("Runtime Mock Exception!"));
		
		try {
			userController.updateUser(user1);
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}

	@Test
	public void testDeleteUser() {
		Mockito.doNothing().when(userService).deleteById(new Long(1L));
		
		String retValue = userController.deleteById(new Long(1L));
		assertNotNull(retValue);
		assertEquals("User deleted successfully!", retValue);
	}
	
	@Test
	public void testDeleteUserException() {
		Mockito.doThrow(new RuntimeException("Runtime Mock Exception!")).when(userService).deleteById(new Long(1L));
		
		try {
			userController.deleteById(new Long(1L));
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}
}
