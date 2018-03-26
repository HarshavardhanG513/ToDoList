package com.harsha.todolist;

public class TodoTaskDTO {
	private Integer id;
	private String name;
	private boolean completed = false;

	public TodoTaskDTO() {
	}

	public TodoTaskDTO(int id, String name, boolean completed) {
		this.id = id;
		this.name = name;
		this.completed = completed;
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

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
