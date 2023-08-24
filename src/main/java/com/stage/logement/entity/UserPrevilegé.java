package com.stage.logement.entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class  UserPrevilegé implements UserDetails { 

	private static final long serialVersionUID = 1L; // l'identifiant de (dé)sérialisation est 1L 
	
	private User user;
	
	public  UserPrevilegé(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		Set<Role> listRoles = user.getRoles();
		
		List<GrantedAuthority> listAuthorities = new ArrayList<>();
		
		for (Role role : listRoles) {
			listAuthorities.add(new SimpleGrantedAuthority(role.getTitle())); // retourne les roles accordés à cet utilisateur 
		}
		
		return listAuthorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() { // vérifie si le compte n'est pas expiré 
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // vérifie si le compte n'est pas verrouillé
		return user.isNotLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() { // vérifie si password n'est pas expiré
		return true;
	}

	@Override
	public boolean isEnabled() { // vérifie si le compte est activé 
		return user.isEnabled();
	}

}


