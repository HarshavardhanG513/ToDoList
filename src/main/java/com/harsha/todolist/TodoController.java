package com.harsha.todolist;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harsha.todolist.Todo.TodoTask;

@Controller
public class TodoController {

	@GetMapping("/greeting")
	public String greeting(
			@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	@GetMapping("/todos")
	public String todos(Model model) {
		model.addAttribute("todos",getDummyData());
		return "todos";
	}
	
	public List<Todo> getDummyData()
	{
		Todo todo=new Todo();
		List<Todo> todos = new ArrayList<Todo>();
		todo.setId(1);
		todo.setName("Travel");
		//todo.getTasks().add(new TodoTask(1,"Backpack",false));
		//todo.getTasks().add(new TodoTask(2,"Clothes",false));
		//todo.getTasks().add(new TodoTask(3,"Medicines",false));
		//todo.getTasks().add(new TodoTask(4,"Food",false));
		todos.add(todo);
		
		todo=new Todo();
		todo.setId(2);
		todo.setName("Shopping List");
		//todo.getTasks().add(new TodoTask(1,"Snacks",false));
		//todo.getTasks().add(new TodoTask(2,"Vegetables",false));
		//todo.getTasks().add(new TodoTask(3,"Medicines",false));
		//todo.getTasks().add(new TodoTask(4,"Stationary",false));
		todos.add(todo);
		return todos;
	}
	@GetMapping("/todo/1")
	public String todo1(Model model) {
		Todo todo =new Todo();
		todo.setId(1);
		todo.setName("Travel");
		todo.getTasks().add(new TodoTask(1,"Backpack",false));
		todo.getTasks().add(new TodoTask(2,"Clothes",false));
		todo.getTasks().add(new TodoTask(3,"Medicines",false));
		todo.getTasks().add(new TodoTask(4,"Food",false));
		model.addAttribute("todo",todo);
		return "todo";
	}
	@GetMapping("/todo/2")
	public String todo2(Model model) {
		Todo todo =new Todo();
		todo.setId(2);
		todo.setName("Shopping List");
		todo.getTasks().add(new TodoTask(1,"Snacks",false));
		todo.getTasks().add(new TodoTask(2,"Vegetables",false));
		todo.getTasks().add(new TodoTask(3,"Medicines",false));
		todo.getTasks().add(new TodoTask(4,"Stationary",false));
		model.addAttribute("todo",todo);
		return "todo";
	}
	
	@GetMapping("/profile")
	public String profile(
			@RequestParam(name = "name", required = false, defaultValue = "none")String name,
			@RequestParam(name = "age",  required = false,defaultValue="0")Integer age,
			@RequestParam(name = "occupation",  required = false,defaultValue="none")String occupation,
			Model model) {
		model.addAttribute("name", name);
		model.addAttribute("age",age);
		model.addAttribute("occupation",occupation);
		return "profile";
	}
}