package com.stage.logement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String path;
	
	@ManyToOne // plusieurs image peut correspondre a un seul logement 
	@JoinColumn(name = "logement_id") // un logement'One' peut avoir plusieurs image 'Many' 
	private logement logement ;
	
	public Image() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public logement getLogement() {
		return logement;
	}

	public void setLogement(logement log) {
		this.logement = log;
	}
	
	
}
