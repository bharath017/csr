package com.csr.security.bean;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class UserBean implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14343L;
	private int user_id;
	private String name;
	private String user_name;
	private String user_password;
	private String user_phone;
	private String user_status;
	private String user_mail;
	private boolean enabled;
	private String[] roles;
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}


	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(roles);
	}

	@Override
	public String getPassword() {
		return this.user_password;
	}

	@Override
	public String getUsername() {
		return this.user_name;
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
		
		return this.enabled;
	}

	@Override
	public String toString() {
		return "UserBean [user_id=" + user_id + ", name=" + name + ", user_name=" + user_name + ", user_password="
				+ user_password + ", user_phone=" + user_phone + ", user_status=" + user_status + ", user_mail="
				+ user_mail + ", enabled=" + enabled + ", roles=" + Arrays.toString(roles) + "]";
	}



}
