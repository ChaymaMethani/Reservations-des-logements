package com.stage.logement.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue; // pour la generation automatique de l'id 
import jakarta.persistence.GenerationType;

@Entity
@Table(name="logement")
public class logement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-increment 
	private Integer id;
	
	@Column(length = 64, nullable = false)
	@NotBlank(message = "name is required ")
	@Length(min = 8, max = 64, message = "name must have 8-64 characters")
	private String name;
	private Boolean flagDisponible;
	
	private double prix;
	
	private String nomproprietaire;
	
	private String description;
	
	@OneToMany(mappedBy = "logement") // un logement peut avoir plusieurs images
	@JsonBackReference 
	private List<Image> img = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "logement") // un logement  peut posséder plusieurs reservations
	@JsonBackReference // pour ne pas sérialiser  aussi toutes les reservations lors de la serialisation de l'objet logement  ne pas tomber en boucle infinie
	private List<Reservation> resv = new ArrayList<>();
	
	@OneToMany(mappedBy = "logement") // un logement  peut posséder plusieurs evaluations
	@JsonBackReference // pour ne pas sérialiser  aussi toutes les evaluations lors de la serialisation de l'objet logement  ne pas tomber en boucle infinie
	private List<evaluation> evaluation = new ArrayList<>();
	
	
	

	public logement (Integer id) {
		this.id = id;
	}

	public logement(
		 String name, Boolean flagDisponible, double d, String nomproprietaire, String description, List<Image> img) {
		
		this.name = name;
		this.flagDisponible = flagDisponible;
		this.prix = d;
		this.nomproprietaire = nomproprietaire;
		this.description = description;
		this.img = img;
	}
	public logement() {}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getFlagDisponible() {
		return flagDisponible;
	}
	public void setFlagDisponible(Boolean flagDisponible) {
		this.flagDisponible = flagDisponible;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(Float prix) {
		this.prix = prix;
	}
	public String getNomproprietaire() {
		return nomproprietaire;
	}
	public void setNomproprietaire(String nomproprietaire) {
		this.nomproprietaire = nomproprietaire;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Image> getImg() {
		return img;
	}
	public void setImg(List<Image> img) {
		this.img = img;
	}
	public List<Reservation> getResv() {
		return resv;
	}
	public void setResv(List<Reservation> resv) {
		this.resv = resv;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<evaluation> getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(List<evaluation> evaluation) {
		this.evaluation = evaluation;
	}
	
	
	
	
	
	
	

}
