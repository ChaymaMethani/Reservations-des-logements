package com.stage.logement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stage.logement.constant.SecurityConstant;
import com.stage.logement.classe.AuthRequest;
import  com.stage.logement.classe.JWT;
import  com.stage.logement.entity.User;
import  com.stage.logement.entity.UserPrevilegé;
import com.stage.logement.exception.DuplicateEmailException;
import com.stage.logement.exception.NotFoundException;
import com.stage.logement.service.UserService;
import com.stage.logement.utility.tokenprovider;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private tokenprovider tokenProvider;
	@Autowired private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JWT> login(@RequestBody @Valid AuthRequest request) throws NotFoundException {
		authenticateUser(request.getEmail(), request.getPassword());
		
		String jwt = tokenProvider.generateJWT(new UserPrevilegé(userService.findByEmail(request.getEmail())));
		
		return new ResponseEntity<>(new JWT(jwt), generateAuthorizationHeader(jwt), HttpStatus.OK);
	}

	private HttpHeaders generateAuthorizationHeader(String jwt) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(SecurityConstant.JWT_HEADER, jwt);
		
		return headers;
	}

	private void authenticateUser(String email, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Valid User user) throws DuplicateEmailException {
		return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
	}
}
