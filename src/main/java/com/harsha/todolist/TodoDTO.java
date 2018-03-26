package com.harsha.todolist;

import java.util.ArrayList;
import java.util.List;

public class TodoDTO {

	public Integer id;
	private String name;
	private List<TodoTaskDTO> tasks = new ArrayList<TodoTaskDTO>();

	public TodoDTO() {

	}

	public TodoDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public List<TodoTaskDTO> getTasks() {
		return tasks;
	}

	public void setTasks(List<TodoTaskDTO> tasks) {
		this.tasks = tasks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}