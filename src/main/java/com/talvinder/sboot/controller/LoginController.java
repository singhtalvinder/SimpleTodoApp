package com.talvinder.sboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.talvinder.sboot.service.LoginService;

@Controller
// pass the model variable name to share across.
@SessionAttributes("userName")
public class LoginController {

	// LoginService: Service to validate logging-in user.
	@Autowired
	LoginService loginService;
	// GET method.
	@RequestMapping(value="/login", method=RequestMethod.GET)
	//@ResponseBody
	public String showLoginPage(ModelMap model) {
		// Data is to be passed through a model from the
		// controller to the view.
		//model.put("name",  userName);
		return "login";
	}

	// Post Method.
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String showWelcomePage(ModelMap model,
			@RequestParam String username,
			@RequestParam String password) {
		// Check if username/pwd is valid . if not redirect to login page.
		// Do it in a seperate service.
		boolean validUser= loginService.validateUser(username, password);
		if(!validUser) {
			// send a validation error to the page as well.
			model.put("validationMessage", "Invalid credentials.");			
			return "login";
		}
			
		
		// Validated.Proceed ahead.
		// Data is to be passed through a model from the
		// controller to the view.
				
		model.put("userName",  username);
		model.put("passWord",  password);
		return "welcome";
	}
}
