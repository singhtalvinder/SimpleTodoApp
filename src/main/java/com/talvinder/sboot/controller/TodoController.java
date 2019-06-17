package com.talvinder.sboot.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.talvinder.sboot.service.TodoService;

@Controller
//Aceess the model variable name here as well.
@SessionAttributes("userName")
public class TodoController {

	// LoginService: Service to validate logging-in user.
	@Autowired
	TodoService service;
	// GET method.
	@RequestMapping(value="/list-todos", method=RequestMethod.GET)
	public String showTodos(ModelMap model) {
		
		// Get the logged-in username and then its activities.
		String uName = (String)model.get("userName");
		
		// Data is to be passed through a model from the
		// controller to the view.
		System.out.println("Calling retrieveTodos.......");
		model.put("todos", service.retrieveTodos(uName));
		return "list-todos";
	}
	
	// Get method.
	@RequestMapping(value="/add-todo", method=RequestMethod.GET)
	public String showaddTodoPage(ModelMap model) {
		return "todo";
	}
	
	// GET method.
	@RequestMapping(value="/add-todo", method=RequestMethod.POST)
	public String addTodo(ModelMap model, @RequestParam String desc) {
		// add it.
		service.addTodo(
				(String)model.get("userName"),
				desc,
				new Date(),
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


}
