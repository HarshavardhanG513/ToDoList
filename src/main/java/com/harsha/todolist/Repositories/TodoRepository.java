package com.harsha.todolist.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import com.harsha.todolist.Entities.Todo;

@Component
public interface TodoRepository extends JpaRepository<Todo,Integer>, JpaSpecificationExecutor<Todo>{

	

}
