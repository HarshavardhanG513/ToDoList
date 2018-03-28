package com.harsha.todolist.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "todo_task")
public class TodoTask {

	@Id
	@Column(name="id")
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="name")
	private String name;

	@Column(name="completed")
	private boolean completed;

	@ManyToOne
	private Todo todo;

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

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}
	
	
}

