package com.harsha.todolist;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import com.harsha.todolist.TodoTaskDTO;
import com.harsha.todolist.Entities.Todo;
import com.harsha.todolist.Entities.TodoTask;
import com.harsha.todolist.Repositories.TodoRepository;
import com.harsha.todolist.Security.CustomUserDetails;
import com.harsha.todolist.TodoDTO;


@Controller
public class TodoController
{
	@Autowired
	TodoRepository todorepository;
	
	@GetMapping("/")
	public String Default()
	{
		return "redirect:/todos";
	}
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,		Model model) 
	{
		model.addAttribute("name", name);
		return "greeting";
	}
	@GetMapping("/todos")
	public String todos(Model model,Authentication authentication) {
		model.addAttribute("todos",getDataFromDatabase(getUserId(authentication)));
		return "todos";
	}
	private List<TodoDTO> getDataFromDatabase(Integer userId) {
		List<Todo> todo = todorepository.findAll();
		List<Todo> todobyid = new ArrayList<Todo>();
		for(int i=0;i<todo.size();i++)
		{		
			if(todo.get(i).getUserId()==userId)
				todobyid.add(todo.get(i));
		}
		List<TodoDTO> tododto = new ArrayList<TodoDTO>();
		for(int i=0;i<todobyid.size();i++)
		{
			tododto.add(mapToTaskDTO(todobyid.get(i)));
		}
		return tododto;
}
	@GetMapping("/todo/{id}")
	public String todo(@PathVariable int id, Model model) {
		TodoDTO todo = getDataFromDatabaseById(id);
		TodoTaskDTO task;
		TodoDTO todo_incomplete = new TodoDTO();
		List<TodoTaskDTO> tasks = new ArrayList<TodoTaskDTO>();
		for(int i=0;i<todo.getTasks().size();i++)
		{	
			task=todo.getTasks().get(i);
			if(!(task.isCompleted()))
			{
				tasks.add(task);
				todo_incomplete.setTasks(tasks);
			}
		}
		todo=todo_incomplete;
		model.addAttribute("todo", todo);
		return "todo";
	}	
	
	private TodoDTO getDataFromDatabaseById(int id) {
		Optional<Todo> findById = todorepository.findById(id);
		return findById.isPresent() ? mapToTaskDTO(findById.get()) : null;	}//if not empty, convert Todo to TodoDTO and return
	
	private TodoDTO mapToTaskDTO(Todo todo) {//converts Todo to TodoDTO
		TodoDTO tododto = new TodoDTO(todo.getId(), todo.getName());
		TodoTask task;
		for (int i=0;i<todo.getTasks().size();i++) {
			task=todo.getTasks().get(i);
			tododto.getTasks().add((new TodoTaskDTO(task.getId(), task.getName(), task.isCompleted())));
		}
		return tododto;
	}
	@ModelAttribute("userId")
	public Integer getUserId(Authentication authentication) {
		CustomUserDetails customUser = (CustomUserDetails) authentication
				.getPrincipal();
		System.out.println("current logged in user id: "
				+ customUser.getUserId());
		return customUser.getUserId();
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