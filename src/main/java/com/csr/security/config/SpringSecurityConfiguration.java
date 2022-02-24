package com.csr.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.csr.security.service.CustomUrlHandler;
import com.csr.security.service.CustomUserDetailService;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true
		)
@ComponentScan("com.csr.security")
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private CustomUserDetailService customerUserDetailService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private CustomUrlHandler customUrlHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
			.antMatchers("/login/**","/static/**").permitAll()
			.antMatchers("/Product/**").hasAnyRole("ADMIN")
			.antMatchers("/Branch/**").hasAnyRole("ADMIN,BRANCH")
			.antMatchers("/User/**").hasAnyRole("ADMIN,USER")
			.antMatchers("/dashboard/**").authenticated()
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login").successHandler(customUrlHandler)
			.failureUrl("/login?invalid=true")
			.usernameParameter("custom_username")
			.passwordParameter("custom_password")
			.permitAll()
			.and()
			.logout()
			.permitAll();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customerUserDetailService);
		authProvider.setPasswordEncoder(encoder);
		return authProvider;
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}
	
	
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
	
	
	

	
}
