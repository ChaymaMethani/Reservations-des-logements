package com.stage.logement.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Message")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-increment 
	private Integer id;
    private String message;
    
    @OneToOne(cascade = CascadeType.ALL) 
	@JoinColumn(name = "user_id") // ici il ya migration de id de la table user à la table message 
	private User communiquant ;

	public Message(String message, User communiquant) {
		
		this.message = message;
		this.communiquant = communiquant;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getCommuniquant() {
		return communiquant;
	}

	public void setCommuniquant(User communiquant) {
		this.communiquant = communiquant;
	}
	
    
    


}
