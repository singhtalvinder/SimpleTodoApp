package com.talvinder.sboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest req,
			                            Exception ex ) {
		ModelAndView mv = new ModelAndView();
		// can also store it in log file or db.
		mv.addObject("exception", ex.getStackTrace()); // ex.getLocalizedMessage()
		mv.addObject("url", req.getRequestURL());
		
		// redirect to error page.
		mv.setViewName("error");
		
		return mv;
		
	}

}
