package com.stage.logement.entity;


import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Reservation")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-increment 
	private Integer id;
	
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private boolean flagConfirmation;
	private boolean flagPaiement;

	@OneToOne(cascade = CascadeType.ALL) // une reservation est effectuer par un seul utilisateur 
	@JoinColumn(name = "user_id") // ici il ya migration de id de la table user   à la table reservation 
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL) // une reservation est effectuer sur un seul logement
	@JoinColumn(name = "logement_id") // ici il ya migration de id de la table logement  à la table reservation 
	private logement logement;
  
	
	

	public Reservation( LocalDate datedebut, LocalDate dateFin, boolean flagConfirmation,
			boolean flagPaiement, User user, logement logement) {
	
		
		this.dateDebut = datedebut;
		this.dateFin = dateFin;
		this.flagConfirmation = flagConfirmation;
		this.flagPaiement = flagPaiement;
		this.user = user;
		this.logement = logement;
	}





	public void setDatedebut(LocalDate datedebut) {
		this.dateDebut = datedebut;
	}
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}
	public LocalDate getDateDebut() {
		return dateDebut;
	}
	public LocalDate getDateFin() {
		return dateFin;
	}

   public boolean getFlagConfirmation() {
		return flagConfirmation;
	}

	public void setFlagConfirmation(Boolean flagConfirmation) {
		this.flagConfirmation = flagConfirmation;
	}

	public boolean getFlagPaiement() {
		return flagPaiement;
	}

	public void setFlagPaiement(Boolean flagPaiement) {
		this.flagPaiement = flagPaiement;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
