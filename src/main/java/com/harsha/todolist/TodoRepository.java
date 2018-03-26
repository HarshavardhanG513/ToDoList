package com.harsha.todolist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
interface TodoRepository extends JpaRepository<Todo,Integer>, JpaSpecificationExecutor<Todo>{

}
