package com.stage.logement.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stage.logement.entity.Role;
import com.stage.logement.entity.User;
import com.stage.logement.entity.UserPrevilegé;
import com.stage.logement.exception.*;
import com.stage.logement.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired private UserRepository repo;
	@Autowired private PasswordEncoder passwordEncode;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException // exception de userDetails 
	{
		User user = repo.findByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found by E-mail: " + email);
		}
		
		user.setLastLoginDate(new Date());
		
		return new UserPrevilegé(user);
	}
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User save(User userInForm) throws DuplicateEmailException {
		boolean isEditMode = userInForm.getId() != null; //veifie si user est en mode edit creation ou mise à jour si id == null creation / mise à jour 
		
		if (isEditMode) { // en mode de mise à jour 
			User userInDB = repo.findById(userInForm.getId()).get(); // recuperation de l'utilisateur de la base 
			
			checkEmail(userInForm /*userediter*/ , userInDB /*user dans la base */);
			checkPassword(userInForm, userInDB);
		}
		// en mode creation 
		checkDuplicateEmail(userInForm.getEmail());
		encodePassword(userInForm);
		
		return repo.save(userInForm); // apres enregistrement renvoie user 
	}
	
	private void checkPassword(User userInForm, User userInDB) {
		boolean isPasswordEmptry = userInForm.getPassword() == null;
		
		if (isPasswordEmptry) { // si password de mise a jour null 
			userInForm.setPassword(userInDB.getPassword()); // on ne  fait pas  la mise a jour du pwd
		}
		
		encodePassword(userInForm); // on encode le nouveau pwd 
	}

	private void checkEmail(User userInForm, User userInDB) throws DuplicateEmailException { // verifie si le mail est le meme saisie dans la base 
		boolean isTheSameEmail = userInDB.getEmail().contentEquals(userInDB.getEmail());
		
		if (isTheSameEmail) {
			userInForm.setEmail(userInDB.getEmail());
		}
		
		checkDuplicateEmail(userInForm.getEmail());
	}

	public User get(Integer id) throws NotFoundException {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new NotFoundException("Could not find any user with id: " + id);
		}
	}
	
	public User findByEmail(String email) throws NotFoundException {
		try {
			return repo.findByEmail(email);
		} catch (NoSuchElementException e) {
			throw new NotFoundException("Could not find any user with email: " + email);
		}
	}
	
	public void delete(Integer id) throws NotFoundException {
		Optional<User> findedUser = repo.findById(id);
		
		if (findedUser.isEmpty()) {
			throw new NotFoundException("Could not find any user with id: " + id);
		}
		
		repo.deleteById(id);
	}

	public User register(User user) throws DuplicateEmailException {
		checkDuplicateEmail(user.getEmail());
		encodePassword(user);
		setUserDetails(user);
		
		return repo.save(user);
	}

	private void setUserDetails(User user) {
		user.setJoinDate(new Date());
		user.setEnabled(true);
		user.setNotLocked(true);
		user.getRoles().add(new Role(2));
	}

	private void encodePassword(User user) { 
		user.setPassword(passwordEncode.encode(user.getPassword()));
	}

	private void checkDuplicateEmail(String email) throws DuplicateEmailException {
		User userInDB = repo.findByEmail(email); // verifie s il existe dans la base user qui posssede un  mail donnee 
		
		if (userInDB != null) { // si user existe 
			throw new DuplicateEmailException("This E-mail: " + email + " already exist. Please choose another E-mail!");
		}
	}
}