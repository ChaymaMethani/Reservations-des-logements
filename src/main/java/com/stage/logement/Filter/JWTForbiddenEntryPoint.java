package com.stage.logement.Filter;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import com.stage.logement.classe.*;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTForbiddenEntryPoint  extends Http403ForbiddenEntryPoint {

	private static final String ACCESS_DENIED = "Access denied. You need to login to access this resource";

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2 // l'exeption de l'authentification par defaut de spring 
			)
			throws IOException {
		HttpResponse httpResponse = new HttpResponse(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value(), ACCESS_DENIED); // instance de la classe httpResponse 
		JsonResponseHelper.writeJsonResponse(response, httpResponse);
	}
	
}