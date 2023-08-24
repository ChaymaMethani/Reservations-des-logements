package com.stage.logement.utility;



import java.time.Instant;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;

import static com.stage.logement.constant.SecurityConstant.*;


import com.auth0.jwt.algorithms.Algorithm;
import com.stage.logement.entity.UserPrevilegé;

@Component
public class tokenprovider {
	
	public String generateJWT(UserPrevilegé userPrincipal) {
		String[] claims = getClaims(userPrincipal); //recupere les donnees de user 
		
		return createToken(userPrincipal, claims); }

	private String createToken(UserPrevilegé userPrincipal , String[] claims) { // creation de jeton 
		return JWT.create() // payload content 
				.withSubject(userPrincipal.getUsername()) // email 'username'
				.withArrayClaim(AUTHORITIES, claims)// un tableau de role 
				.withIssuer(ISSUER)  // l'emetteur 
				.withAudience(AUDIENCE) // le recepteur 
				.withIssuedAt(Instant.now()) // le  moment ou le jeton est emis le moment actuel
				.withExpiresAt(Instant.now().plusMillis(EXPIRATION_TIME)) // date d'expiration 
				.sign(Algorithm.HMAC512(JWT_SECRET)); // l'ago de hachage pour signer le jeton avec la cle secret 
	}

	private String[] getClaims(UserPrevilegé userPrincipal) { // retourne un tableau de role d'un user donnee 
		return userPrincipal.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.toArray(String[]::new);
	}
}

