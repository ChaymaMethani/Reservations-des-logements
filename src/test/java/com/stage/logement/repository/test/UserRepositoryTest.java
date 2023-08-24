package com.stage.logement.repository.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;


import com.stage.logement.entity.Role;
import com.stage.logement.entity.User;
import com.stage.logement.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired private UserRepository repo;
	@Autowired private PasswordEncoder passwordEncoder;
	
	private User user_1;
	private User user_2;
	private User user_3;

	
	
	
	@BeforeEach
	void init() {
		String password = "password";
		String encodedPassword = passwordEncoder.encode(password);
		
		
		
		user_1 = new User("admin@yahoo.com",encodedPassword  , "Admin", "Admin",new Date(), new Date(), true, true, "Manouba", new Role(1));
		user_2 = new User("user@yahoo.com", encodedPassword, "user1", "user", new Date(), new Date(), true, true, "Iben Khaldoun", new Role(2));
		user_3 = new User("agent@yahoo.com", encodedPassword, "agent", "agent", new Date(), new Date(), true, true, "address3", new Role(3));
		
	}
	
	@Test
	void save() {
	 User savedUser = repo.save(user_1);
		
		assertNotNull(savedUser);
		assertThat(savedUser.getId()).isGreaterThan(0);
		assertEquals(savedUser.getEmail(), user_1.getEmail());
	}

	@Test
	void save2() {
	 User savedUser = repo.save(user_3);
		
		assertNotNull(savedUser);
		assertThat(savedUser.getId()).isGreaterThan(0);
		assertEquals(savedUser.getEmail(), user_3.getEmail());
	}
	
	@Test
	void listAll() {
		List<User> listUsers = repo.findAll();
		
		assertNotNull(listUsers);
		
		listUsers.forEach(user -> System.out.println(user));
	}
	
	@Test
	void get() {
		Integer id =4 ;
		
		User findedUser = repo.findById(id).get();
		
		assertNotNull(findedUser);
		assertEquals(user_2.getEmail(), findedUser.getEmail());
	}
	
	@Test
	void update() {
		Integer id = 4;
		String firstName = "user1";
		
		User findedUser = repo.findById(id).get();
		findedUser.setFirstName(firstName);
		
		User updatedUser = repo.save(findedUser);
		
		assertEquals(firstName, updatedUser.getFirstName());
	}
	
  @Test
   void delete() {
		Integer id = 11;
		
		repo.deleteById(id);
		
		Optional<User> findedUser = repo.findById(id);
		
		assertThat(!findedUser.isPresent()); // verifie si user supprimé n'est pas present dans la base  
	}
	
	@Test
	 void findByEmail() {
		String email = "user@yahoo.com";
		
		User findedUser = repo.findByEmail(email);
		
	        assertNotNull(findedUser);
		     assertEquals(email, findedUser.getEmail());
	}
}













