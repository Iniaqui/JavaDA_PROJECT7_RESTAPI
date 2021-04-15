package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.execptions.RessourceNotFoundException;
import com.nnk.springboot.repositories.UserRepository;

@Service
@Transactional
public class UserServices implements CrudInterface<User>, UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User data) {
		// TODO Auto-generated method stub
		return userRepository.save(data);
	}

	@Override
	public User update(User data) {
		// TODO Auto-generated method stub
		Optional<User> userDB = this.userRepository.findById(data.getId());
		if(userDB.isPresent()) {
			User userUpdate = userDB.get();
			userUpdate.setFullname(data.getFullname());
			userUpdate.setId(data.getId());
			userUpdate.setPassword(data.getPassword());
			userUpdate.setRole(data.getRole());
			userUpdate.setUsername(data.getUsername());
			this.userRepository.save(userUpdate);
			return userUpdate;
		}
		else {
			throw new RessourceNotFoundException("User not found with id = " +data.getId());
		}
	}

	@Override
	public User readById(int id) {
		// TODO Auto-generated method stub
		Optional<User> userDB = this.userRepository.findById(id);
		if(userDB.isPresent()) {
			return userDB.get();
		}
		else {
			throw new RessourceNotFoundException("User not found with id = " +id);
		}
	}

	@Override
	public User readByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleted(int id) {
		// TODO Auto-generated method stub
		Optional<User> userDB = this.userRepository.findById(id);
		if(userDB.isPresent()) {
			this.userRepository.delete(userDB.get());
		}
		else {
			throw new RessourceNotFoundException("User not found with id = " +id);
		}
	}

	@Override
	public List<User> getAllData() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.print("Appel de la classe userServices");
		User user= userRepository.getByUserName(username);
		 if(user == null)
				
	        {
	
	            throw new UsernameNotFoundException(username);
	
	        }	
	        return user;
	}

	
}
