package com.harsha.todolist;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import com.harsha.todolist.TodoTaskDTO;
import com.harsha.todolist.TodoDTO;
import com.harsha.todolist.Todo;
import com.harsha.todolist.TodoTask;
import com.harsha.todolist.TodoRepository;


@Controller
public class TodoController
{
	@Autowired
	TodoRepository todorepository;
	
	@GetMapping("/greeting")
	public String greeting(
			@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	@GetMapping("/todos")
	public String todos(Model model) {
		model.addAttribute("todos",getDataFromDatabase());
		return "todos";
	}
	private List<TodoDTO> getDataFromDatabase() {
		return todorepository.findAll().stream().map(t -> {
			return mapToTaskDTO(t);
		}).collect(Collectors.toList());
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
		return findById.isPresent() ? mapToTaskDTO(findById.get()) : null;	}
	
	private TodoDTO mapToTaskDTO(Todo todo) {
		TodoDTO todo1 = new TodoDTO(todo.getId(), todo.getName());
		for (TodoTask task : todo.getTasks()) {
			todo1.getTasks().add(new TodoTaskDTO(task.getId(), task.getName(), task.isCompleted()));
		}
		return todo1;
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