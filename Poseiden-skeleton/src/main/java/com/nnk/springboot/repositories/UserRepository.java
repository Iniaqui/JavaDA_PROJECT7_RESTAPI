package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.User;
/**
 *  Repository inheriting from the JpaRepository class to process information with the database 
 * @author maure
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {	
	@Query(name = "User.getByUserName",value = "select u from User u where u.username = :username")
	User getByUserName(
			@Param("username") String username);
}
