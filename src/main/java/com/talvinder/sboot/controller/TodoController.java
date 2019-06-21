package com.talvinder.sboot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.talvinder.sboot.model.Todo;
import com.talvinder.sboot.service.TodoService;

@Controller
//Access the model variable name here as well.
//@SessionAttributes("userName")
public class TodoController {

	// LoginService: Service to validate logging-in user.
	@Autowired
	TodoService service;
	
	// To bind the form properties to the data properties with correct data types.
	@InitBinder
	public void InitBinder(WebDataBinder webBinder) {
		// we can specify the date format to use throughout.
		//Date - dd/MM/yyyy.
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		webBinder.registerCustomEditor(Date.class,
										new CustomDateEditor(dateFormat, false));
		
	}
	
	// GET method.
	@RequestMapping(value="/list-todos", method=RequestMethod.GET)
	public String showTodos(ModelMap model) {
		
		// Get the logged-in username and then its activities.
		String uName = getLoggedInUserName(model);
		
		// Data is to be passed through a model from the
		// controller to the view.
		System.out.println("Calling retrieveTodos.......");
		model.put("todos", service.retrieveTodos(uName));
		return "list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		// In Spring, loggedinuser is called principal.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		// check if this principal is an instance of spring defined UserDetails class.
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		
		//Not found case.
		return principal.toString();
	}
	
	// Get method.
	@RequestMapping(value="/add-todo", method=RequestMethod.GET)
	public String showaddTodoPage(ModelMap model) {
		// The name in addAttribute should map to the 'modelAttribute'
		// in the jsp form.
		model.addAttribute("todo",
								new Todo(0, 
										getLoggedInUserName(model),
										"describe your task",
										new Date(),
										false));
		return "todo";
	}
	
	// GET method.
	@RequestMapping(value="/add-todo", method=RequestMethod.POST)
	// The todo here must map to the form modelAttribute to work.
	//BindingResult is populated if using @Valid. 
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		// Check if there were any validation errors.
		if(result.hasErrors()) {
			// go back to todo page.
			return "todo";
		}
		// add it.
		service.addTodo(
				getLoggedInUserName(model),
				todo.getDesc(),
				todo.getTargetDate(),
				false);
	
		// redirect to list-todos so that the todos are accessible.
		return "redirect:/list-todos";
	}

	// Get method.
	@RequestMapping(value="/delete-todo", method=RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	// Get method.
	@RequestMapping(value="/update-todo", method=RequestMethod.GET)
	public String showUpdateTodoPage(ModelMap model, @RequestParam int id) {
		// retrieve for current user.
		Todo todo = service.retrieveTodo(id);
		model.put("todo", todo);
		
		// and redirect to todo page.
		return "todo";
	}
	
	// Post method.
	@RequestMapping(value="/update-todo", method=RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		// Check if there were any validation errors.
		if(result.hasErrors()) {
			// go back to todo page.
			return "todo";
		}
		
		// Get the user for this task.
		todo.setUser(getLoggedInUserName(model));

		
		// retrieve for current user.
		service.updateTodo(todo);
		
		// Redirect to todo page.
		return "redirect:/list-todos";
	}


}
