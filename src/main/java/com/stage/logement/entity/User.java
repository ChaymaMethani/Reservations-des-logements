package com.stage.logement.entity;

import java.util.ArrayList;
import java.util.Date; // pour la date actuelle 
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference; // pour ne pas tomber en boucle infinie dans la (dé)sérialisation d'un objet 
import org.hibernate.validator.constraints.Length; // pour le controle de la longueur des champs 

 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue; // pour la generation automatique de l'id 
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; // pour utiliser @Id 
import jakarta.persistence.JoinColumn; // pour utiliser @Join Column 
import jakarta.persistence.JoinTable; // pour utiliser @Join table  
import jakarta.persistence.ManyToMany; // plusieurs à plusieurs 
import jakarta.persistence.OneToMany; // relation un a plusieurs 

import jakarta.persistence.Table;    // pour utiliser @table 
import jakarta.validation.constraints.Email; // pour controle de la forme du mail
import jakarta.validation.constraints.NotBlank; // pour ne pas saisir des caractéres blancs


@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-increment 
	private Integer id;

	@Column(length = 64, nullable = false, unique = true)
	@Email(message = "E-mail is not valid")
	@Length(min = 8, max = 64, message = "E-mail must have 8-64 characters")
	private String email;

	@Column(length = 64, nullable = false)
	@NotBlank(message = "Password is required")
	@Length(min = 8, max = 64, message = "Password must have 8-64 characters")
	private String password;

	@Column(length = 35, nullable = false)
	@NotBlank(message = "First name is required")
	@Length(min = 3, max = 30, message = "First name mush have between 3 and 30 characters")
	private String firstName;

	@Column(length = 45, nullable = false)
	@NotBlank(message = "First name is required")
	@Length(min = 3 , max = 45, message = "First name mush have between 3 and 45 characters")
	private String lastName;

	private Date joinDate; 

	private Date lastLoginDate;

	private boolean isEnabled; // le compte user est activé 

	private boolean isNotLocked; // le compte user n'est pas verrouillé 

	
	private String address;

	@ManyToMany
	@JoinTable( 
		name = "users_roles",  // l'association manytomany  permet d'obtenir une table 
		joinColumns = @JoinColumn(name = "user_id"), // on a la table user_role qui traduit la relation entre la table user et la table role 
		inverseJoinColumns = @JoinColumn(name = "role_id")) // un utilisateur peut avoir plusieurs roles et un role peut correspondre a plusieurs user
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user") // un utilisateur peut effectuer plusieurs reservations
	@JsonBackReference // pour ne pas sérialiser  aussi toutes les reservations lors de la serialisation de l'objet user ne pas tomber en boucle infinie
	private List<Reservation> resv = new ArrayList<>();
	
	@OneToMany(mappedBy = "user") // un utilisateur peut effectuer plusieurs reservations
	@JsonBackReference // pour ne pas sérialiser  aussi toutes les reservations lors de la serialisation de l'objet user ne pas tomber en boucle infinie
	private List<paiement> paiement = new ArrayList<>();
	
	@OneToMany(mappedBy = "user") // un utilisateur peut effectuer plusieurs evaluations
	@JsonBackReference // pour ne pas sérialiser  aussi toutes les evaluations lors de la serialisation de l'objet user ne pas tomber en boucle infinie
	private List<evaluation> evaluation = new ArrayList<>();
	
	@OneToMany(mappedBy = "communiquant") // un utilisateur peut envoyer/recevoir plusieurs message
	@JsonBackReference // pour ne pas sérialiser  aussi toutes les evaluations lors de la serialisation de l'objet user ne pas tomber en boucle infinie
	private List<Message> message= new ArrayList<>();
	

	public User() {}

	public User(Integer id) {
		this.id = id;
	}

	public User(String email, String password, String firstName, String lastName, Date joinDate, Date lastLoginDate,
			boolean isEnabled, boolean isNotLocked, String address, Role role) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.joinDate = joinDate;
		this.lastLoginDate = lastLoginDate;
		this.isEnabled = isEnabled;
		this.isNotLocked = isNotLocked;
		this.address = address;
		this.roles.add(role);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public boolean isNotLocked() {
		return isNotLocked;
	}

	public void setNotLocked(boolean isNotLocked) {
		this.isNotLocked = isNotLocked;
	}

	public String Address() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Reservation> getReservations() {
		return resv;
	}

	public void setRents(List<Reservation> res) {
		this.resv = res;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", joinDate=" + joinDate + ", lastLoginDate=" + lastLoginDate
				+ ", isEnabled=" + isEnabled + ", isNotLocked=" + isNotLocked + ", address=" + address + ", roles="
				+ roles + "]";
	}
}
