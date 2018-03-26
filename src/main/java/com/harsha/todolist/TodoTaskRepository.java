package com.harsha.todolist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TodoTaskRepository extends JpaRepository<Todo,Integer>, JpaSpecificationExecutor<Todo>{

}
