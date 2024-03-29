package com.talvinder.sboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	// create users/pwds - ashmeet/a123
	
	//create login form and not use the default one.
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication()
			.withUser("ashmeet")
			.password("{noop}a123456")
			.roles("USER", "ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll()
				.antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
				.formLogin();
	}

}
