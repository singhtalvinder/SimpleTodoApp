package com.talvinder.sboot.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// pass the model variable name to share across.
//@SessionAttributes("userName")
@Controller
public class WelcomeController {
	
	// GET method.
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		// Data is to be passed through a model from the
		// controller to the view.
		model.put("userName", getLoggedinUserName());
		return "welcome";
	}
	
	private String getLoggedinUserName() {
		// In Spring, loggedinuser is called principal.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		// check if this principal is an instance of spring defined UserDetails class.
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		
		//Not found case.
		return principal.toString();
	}
}

//	// Post Method.
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String showWelcomePage(ModelMap model,
//			@RequestParam String username,
//			@RequestParam String password) {
//		// Check if username/pwd is valid . if not redirect to login page.
//		// Do it in a seperate service.
//		boolean validUser= loginService.validateUser(username, password);
//		if(!validUser) {
//			// send a validation error to the page as well.
//			model.put("validationMessage", "Invalid credentials.");			
//			return "login";
//		}
//			
//		
//		// Validated.Proceed ahead.
//		// Data is to be passed through a model from the
//		// controller to the view.
//				
//		model.put("userName",  username);
//		model.put("passWord",  password);
//		return "welcome";
//	}

