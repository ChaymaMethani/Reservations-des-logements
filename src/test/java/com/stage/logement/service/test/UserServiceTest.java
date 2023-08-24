package com.stage.logement.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import com.stage.logement.entity.Role;
import com.stage.logement.entity.User;
import com.stage.logement.exception.DuplicateEmailException;
import com.stage.logement.exception.NotFoundException;
import com.stage.logement.repository.UserRepository;
import com.stage.logement.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	private UserService service;
	
	@Mock
	private UserRepository repo;
	
	private User user_1;
	private User user_2;
	
	
	
	@BeforeEach
	void init() {
		
		
		
		user_1 = new User("admin@yahoo.com", "password", "Admin", "Admin", new Date(), new Date(), true, true, "Manouba", new Role(1));
		user_2 = new User("user@yahoo.com","password" , "user", "user", new Date(), new Date(), true, true, "Iben khaldoun", new Role(2));
		
	}
	
	@Test
	void findAll() {
		when(repo.findAll()).thenReturn(List.of(user_1, user_2));
		
		List<User> listUsers = service.findAll();
		
		assertNotNull(listUsers);
		assertEquals(2, listUsers.size());
		
		listUsers.forEach(user -> System.out.println(user));
	}
	
	@Test
	void save() throws DuplicateEmailException {
		when(repo.save(any(User.class))).thenReturn(user_2);
		
		User savedUser = service.save(user_2);
		
		assertNotNull(savedUser);
		assertEquals(user_2.getEmail(), savedUser.getEmail());
	}
	
	@Test
	void get() throws NotFoundException {
		Integer id = 1;
		
		when(repo.findById(anyInt())).thenReturn(Optional.of(user_1));
		
		User findedUser = service.get(id);
		
		assertNotNull(findedUser);
	}
	
	@Test
	void getForException() {
		when(repo.findById(1)).thenReturn(Optional.of(user_1));
		
		assertThrows(Exception.class, () -> {
			service.get(2);
		});
	}
	
	
	
}