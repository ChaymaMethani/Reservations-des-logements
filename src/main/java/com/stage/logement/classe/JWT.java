package com.stage.logement.classe;
import java.time.Instant;

import static com.stage.logement.constant.SecurityConstant.*; // pour recupérer la constane EXPIRATION_TIME 

public class JWT {

	private String jwt; //token 
	
	private Instant expirantionDate; // objet de la classe Instant de java 
	
	public JWT() {
		
	}

	public JWT(String jwt) {
		this.jwt = jwt;
		this.expirantionDate = Instant.now().plusMillis(EXPIRATION_TIME);
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public Instant getExpirantionDate() {
		return expirantionDate;
	}

	public void setExpirantionDate(Instant expirantionDate) {
		this.expirantionDate = expirantionDate;
	}

	@Override
	public String toString() {
		return "JWT [jwt=" + jwt + ", expirantionDate=" + expirantionDate + "]";
	}
}
