package com.csr.security.service;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("customUrlHandler")
public class CustomUrlHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Set<String> authorities=AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if(authorities.contains("ROLE_ADMIN")) {
			response.sendRedirect("dashboard");
		}else if(authorities.contains("ROLE_SUPERADMIN")) {
			response.sendRedirect("home/AdminDashboard");
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}

	
}
