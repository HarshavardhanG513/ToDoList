package com.harsha.todolist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.harsha.todolist.Todo.TodoTask;
import com.premkumar.todo.service.Todo;

@Controller
public class TodoController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
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
	private List<Todo> getDataFromDatabase() {
		String select = "select id,name from todo";
		List<Todo> data = jdbcTemplate.query(select,((rs, rowNum) -> new Todo(rs.getInt("id"), rs.getString("name"))));
		return CollectionUtils.isEmpty(data) ? Collections.<Todo>emptyList(): data;//if list is empty it returns empty list otherwise returns data
}
		
	private Todo getDataFromDatabaseById(int id) {
		Todo todo = null;
		String select = "select id,name from todo where id=?";
		List<Todo> allTasks=jdbcTemplate.query(select,new PreparedStatementSetter() {//PreparedStatementSetter - used instead of some of the parameters of query
			@Override
			public void setValues(PreparedStatement ps) 
			{
				try {
					ps.setInt(1, id);
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		},((rs, rowNum) -> new Todo(rs.getInt("id"), rs.getString("name"))));
		if(CollectionUtils.isEmpty(allTasks))
			return null;//return null if its empty
		select="select id, name,completed from todo_task where todo_id=?";
		todo=allTasks.get(0);//we only want incomplete tasks (with value=0)
		List<Todo> tasks = jdbcTemplate.query(select,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) 
			{
				try {
					ps.setInt(1, id);
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		},((rs,rowNo)->new TodoTask(rs.getInt("id"),rs.getString("name"),rs.getBoolean("completed"))));
		todo.setTasks(tasks);
		return todo;
		
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