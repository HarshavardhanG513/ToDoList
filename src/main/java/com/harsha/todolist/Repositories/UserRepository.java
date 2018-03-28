package com.harsha.todolist.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.harsha.todolist.Entities.User;

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User>{

	User findByUsername(String username);
}
