package com.stage.logement.entity;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	
	@ManyToMany
	@JoinTable(
		name = "users_roles", 
		joinColumns = @JoinColumn(name = "role_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users = new HashSet<>(); //pour un role on a une liste de user et un user possede une liste de role 
	
	public Role() {}
	
	public Role(Integer id) {
		this.id = id;
	}

	public Role(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title ;
	}

	public void settitle (String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", title=" + title + "]";
	}
	
	
}
