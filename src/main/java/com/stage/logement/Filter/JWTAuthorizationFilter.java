package com.stage.logement.Filter;

import static com.stage.logement.constant.SecurityConstant.*;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.stage.logement.utility.tokenverifier;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter  /*classe executé une seule fois pour chaque requêteHTTP */
{
	
	@Autowired private tokenverifier tokenVerifier; // injection de dependance pour verifier le jeton recuperé de l'entête 
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if (isOptionsMethod(request)) {  // si la methode  http est options accés direct a la ressource
			response.setStatus(HttpStatus.OK.value()); // le code est 200
		} else if (!isPublicRoute(request)) { // si la rout est privé necessit authentification 
			String token = getTokenFromHeader(request); // extraction du token de l'entête de la requette 
			String email = tokenVerifier.getSubject(token); // retourne le subj identifiant 
			
			tokenVerifier.verifyTokenAndBuildAuthentication(email, token, request); // construire l'authentification a cet ressource 
		}
			
		filterChain.doFilter(request, response); // passe la requette a d'autre filter 
	}
	
	private boolean isPublicRoute(HttpServletRequest request) { // verifie si la route est publique ou non 
		for (String publicUrl : PUBLIC_URLS) {
			if (request.getServletPath().startsWith(publicUrl)) {
				return true;
			}
		}
		return false;
	}

	private String getTokenFromHeader(HttpServletRequest request) { // extraction du jwt de l'entête de la requette 
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if (authorizationHeader == null || !authorizationHeader.startsWith(JWT_PREFIX)) {
			return null;
		}
		
		return authorizationHeader.substring(JWT_PREFIX.length());
	}

	private boolean isOptionsMethod(HttpServletRequest request) {
		return request.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD);
	}

}
