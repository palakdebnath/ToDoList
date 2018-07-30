package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserDao userDao;

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
		when(userDao.getAllUser()).thenReturn(userList);
		
		List<User> userListReturned = userService.getAllUser();
		assertNotNull(userListReturned);
		assertEquals(2, userListReturned.size());
	}
	
	@Test
	public void testGetAllUserException() {
		when(userDao.getAllUser()).thenThrow(new RuntimeException("Runtime Mock Exception!"));
		
		try {
			userService.getAllUser();
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}

	@Test
	public void testCreateUser() {
		when(userDao.createUser(Mockito.any())).thenReturn(user1);
		
		User userCreated = userService.createUser(user1);
		assertNotNull(userCreated);
		assertEquals(new Long(1L), userCreated.getUserId());
		assertEquals("palakdebnath", userCreated.getUserName());
		assertEquals("Palak", userCreated.getFirstName());
		assertEquals("Debnath", userCreated.getLastName());
	}
	
	@Test
	public void testCreateUserException() {
		when(userDao.createUser(Mockito.any())).thenThrow(new RuntimeException("Runtime Mock Exception!"));
		
		try {
			userService.createUser(user1);
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}
	
	@Test
	public void testUpdateUser() {
		
		user1.setUserName("palakdebnath111");
		when(userDao.updateUser(Mockito.any())).thenReturn(user1);
		
		User userUpdated = userService.updateUser(user1);
		assertNotNull(userUpdated);
		assertEquals(new Long(1L), userUpdated.getUserId());
		assertEquals("palakdebnath111", userUpdated.getUserName());
		assertEquals("Palak", userUpdated.getFirstName());
		assertEquals("Debnath", userUpdated.getLastName());
	}
	
	@Test
	public void testUpdateUserException() {
		when(userDao.updateUser(Mockito.any())).thenThrow(new RuntimeException("Runtime Mock Exception!"));
		
		try {
			userService.updateUser(user1);
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}


	@Test
	public void testDeleteUser() {
		Mockito.doNothing().when(userDao).deleteById(new Long(1L));
		
		userService.deleteById(new Long(1L));
		verify(userDao, times(1)).deleteById(new Long(1L));
	}
	
	@Test
	public void testDeleteUserException() {
		Mockito.doThrow(new RuntimeException("Runtime Mock Exception!")).when(userDao).deleteById(new Long(1L));
		
		try {
			userService.deleteById(new Long(1L));
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}
}
