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
@Table(name="paiement")
public class paiement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-increment 
	private Integer id;
  
	private String modePaiement;
	
	@OneToOne(cascade = CascadeType.ALL) // un paiement correspond a un seul user 
	@JoinColumn(name = "user_id") // ici il ya migration de id de la table User à la table paiement
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL) // un paiement correspond a une reservation
	@JoinColumn(name = "reservation_id") // ici il ya migration de id de la table reservation à la table paiement
	private Reservation reservation;

	public paiement(String modePaiement, User user, Reservation reservation) {
		super();
		this.modePaiement = modePaiement;
		this.user = user;
		this.reservation = reservation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	public paiement() {}
	
	
}
