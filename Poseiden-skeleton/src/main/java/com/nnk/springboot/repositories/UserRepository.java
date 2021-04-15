package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {	
	@Query(name = "User.getByUserName",value = "select u from User u where u.username = ?1" ,nativeQuery = true)
	User getByUserName(String username);
}
