package com.sparta.andbackoffice.security;

import com.sparta.andbackoffice.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

	private final Admin admin;

	public UserDetailsImpl(Admin admin) {
		this.admin = admin;
	}

	public Admin getUser() {
		return admin;
	}

	public Long AdminId(){
		return admin.getId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return admin.getAdminPassword();
	}

	@Override
	public String getUsername() {
		return admin.getAdminName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}
