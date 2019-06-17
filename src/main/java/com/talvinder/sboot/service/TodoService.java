package com.talvinder.sboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.talvinder.sboot.model.Todo;


@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "ashmeet", "Learn Spring MVC", new Date(),
                false));
        todos.add(new Todo(2, "ashmeet", "Learn Struts", new Date(), false));
        todos.add(new Todo(3, "ashmeet", "Learn Hibernate", new Date(),
                false));
    }

    public List<Todo> retrieveTodos(String user) {
    	System.out.println("Inside retrieveTodos.....");
    	System.out.println("find user = " + user);
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
        	// Generally good to not allow case insensitive usernames.
            if (todo.getUser().equalsIgnoreCase(user)) {
            	System.out.println("user = " + user +" todo:user= " + todo.getUser());
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public void addTodo(String name, String desc, Date targetDate,
            boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
}