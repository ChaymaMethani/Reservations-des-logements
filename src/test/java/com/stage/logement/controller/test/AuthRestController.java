package com.stage.logement.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.stage.logement.classe.AuthRequest;
import com.stage.logement.classe.JWT;
import com.stage.logement.entity.User;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthRestController{
	
	private static String URI;

	@LocalServerPort
	private Integer port;
	
	private RestTemplate restTemplate;
	
	@BeforeEach
	public void init() {
		URI = String.format("http://localhost:%d/auth", port);
		restTemplate = new RestTemplate();
	}
	
	@Test
	void login() {
		String email = "admin@yahoo.com";
		String password = "password";
		
		AuthRequest authRequest = new AuthRequest(email, password);
		
		ResponseEntity<JWT> response = restTemplate.postForEntity(URI + "/login", authRequest, JWT.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody().getJwt());
	}
	
	@Test
	void register() {
		User user = new User("user3@gmail.com", "password", "User", "100", null, null, false, false, null, null);
		
		ResponseEntity<User> response = restTemplate.postForEntity(URI + "/register", user, User.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}
}



