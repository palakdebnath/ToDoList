package com.example.demo.dao;

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

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
	
	@InjectMocks
	private UserDao userDao;
	
	@Mock
	private UserRepository userRepo;

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
		when(userRepo.findAll()).thenReturn(userList);
		
		List<User> userListReturned = userDao.getAllUser();
		assertNotNull(userListReturned);
		assertEquals(2, userListReturned.size());
	}
	
	@Test
	public void testGetAllUserException() {
		when(userRepo.findAll()).thenThrow(new RuntimeException("Runtime Mock Exception!"));
		
		try {
			userDao.getAllUser();
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}

	@Test
	public void testCreateUser() {
		when(userRepo.save(Mockito.any())).thenReturn(user1);
		
		User userCreated = userDao.createUser(user1);
		assertNotNull(userCreated);
		assertEquals(new Long(1L), userCreated.getUserId());
		assertEquals("palakdebnath", userCreated.getUserName());
		assertEquals("Palak", userCreated.getFirstName());
		assertEquals("Debnath", userCreated.getLastName());
	}
	
	@Test
	public void testCreateUserException() {
		when(userRepo.save(Mockito.any())).thenThrow(new RuntimeException("Runtime Mock Exception!"));
		
		try {
			userDao.createUser(user1);
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}
	
	@Test
	public void testUpdateUser() {
		
		user1.setUserName("palakdebnath111");
		when(userRepo.save(Mockito.any())).thenReturn(user1);
		
		User userUpdated = userDao.updateUser(user1);
		assertNotNull(userUpdated);
		assertEquals(new Long(1L), userUpdated.getUserId());
		assertEquals("palakdebnath111", userUpdated.getUserName());
		assertEquals("Palak", userUpdated.getFirstName());
		assertEquals("Debnath", userUpdated.getLastName());
	}
	
	@Test
	public void testUpdateUserException() {
		when(userRepo.save(Mockito.any())).thenThrow(new RuntimeException("Runtime Mock Exception!"));
		
		try {
			userDao.updateUser(user1);
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}

	@Test
	public void testDeleteUser() {
		Mockito.doNothing().when(userRepo).deleteById(new Long(1L));
		
		userDao.deleteById(new Long(1L));
		verify(userRepo, times(1)).deleteById(new Long(1L));
	}
	
	@Test
	public void testDeleteUserException() {
		Mockito.doThrow(new RuntimeException("Runtime Mock Exception!")).when(userRepo).deleteById(new Long(1L));
		
		try {
			userDao.deleteById(new Long(1L));
		} catch(Exception ex) {
			assertNotNull(ex);
			assertEquals("Runtime Mock Exception!", ex.getMessage());
		}
	}
}
