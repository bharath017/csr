package com.csr.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.csr.security.bean.UserBean;
import com.csr.security.dao.UserDAOImpl;

@Component("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	public UserDAOImpl dao;	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserBean bean=dao.getUserDetailsByUserName(username);
		System.out.println(bean);
		return bean;
	}

	
	
}
