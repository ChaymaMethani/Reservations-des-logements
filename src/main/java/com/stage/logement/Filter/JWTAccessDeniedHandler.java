package com.stage.logement.Filter;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.stage.logement.classe.HttpResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAccessDeniedHandler implements AccessDeniedHandler { 

	private static final String ACCESS_DENIED = "Access denied. You do not have sufficient permissions to access this resource";

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		HttpResponse httpResponse = new HttpResponse(HttpStatus.UNAUTHORIZED // le code 401   
				, HttpStatus.UNAUTHORIZED.value() // la valeur de statut
				,ACCESS_DENIED.toUpperCase()); // le message qui sera afficher // creation de cet objet lorsque user ne possede pas les authorisations necessaires 
		JsonResponseHelper.writeJsonResponse(response, httpResponse);
		// transformation de tout en format JSON precise par la methode WriteJsonResponse de la classe JsonResponseHelper 
	}

} // cette classe permet de fournir une reponse sous format JSON au utilisateur authentifier mais non pas acces d'authorisation pour acceder a une ressource 

