package com.talvinder.sboot.service;

import org.springframework.stereotype.Component;

//Spring Bean.- managed by spring.
@Component
public class LoginService {
	public boolean validateUser(String userId, String password) {
		// dummy valid data.
		return userId.equalsIgnoreCase("ashmeet") &&
				password.equalsIgnoreCase("a123");
	}
}
