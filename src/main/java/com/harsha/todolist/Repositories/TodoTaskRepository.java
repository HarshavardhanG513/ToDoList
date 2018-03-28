package com.harsha.todolist.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.harsha.todolist.Entities.Todo;

public interface TodoTaskRepository extends JpaRepository<Todo,Integer>, JpaSpecificationExecutor<Todo>{

}
