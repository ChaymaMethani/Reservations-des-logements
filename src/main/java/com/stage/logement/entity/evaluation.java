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
@Table(name="evaluation")

public class evaluation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-increment 
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL) // une evaluation correspond a un seul user 
	@JoinColumn(name = "user_id") // ici il ya migration de id de la table User à la table evaluation 
	private User user;
	

	@OneToOne(cascade = CascadeType.ALL) // une evaluation correspond a un seul logement
	@JoinColumn(name = "logement_id") // ici il ya migration de id de la table logement à la table evaluation 
	private logement logement;
	
	private String valeur ;
	private String commentaire;
	public evaluation(User user, com.stage.logement.entity.logement logement, String valeur, String commentaire) {
		super();
		this.user = user;
		this.logement = logement;
		this.valeur = valeur;
		this.commentaire = commentaire;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public logement getLogement() {
		return logement;
	}
	public void setLogement(logement logement) {
		this.logement = logement;
	}
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	
	

}
