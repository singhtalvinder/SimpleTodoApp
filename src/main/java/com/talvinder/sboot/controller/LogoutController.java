package com.talvinder.sboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// pass the model variable name to share across.
//@SessionAttributes("userName")
@Controller
public class LogoutController {
	
	// GET method.
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest req, HttpServletResponse res ) {
		 
		//Get user authentication details.
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		 if(auth != null) {
			 new SecurityContextLogoutHandler().logout(req, res, auth);
		 }


		 // Logged out, redirect to login page. 
		 return "redirect:/";
	}
	
}