package com.harsha.todolist;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
public class Todo {

	@Id
	@Column(name="id")
	private Integer id;

	@Column(name="name")
	private String name;

	@Column(name="userId")
	private Integer userId;

	@OneToMany(mappedBy = "todo")
	private List<TodoTask> tasks = new ArrayList<TodoTask>();

	public List<TodoTask> getTasks() {
		return tasks;
	}

	public void setTasks(List<TodoTask> tasks) {
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
