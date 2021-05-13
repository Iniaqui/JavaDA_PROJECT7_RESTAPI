package com.nnk.springboot.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.nnk.springboot.domain.User;
import com.nnk.springboot.execptions.RessourceNotFoundException;
import com.nnk.springboot.repositories.UserRepository;
/**
 * Department responsible for the application of CRUD methods 
 * @see User
 * @author maure
 *
 */
@Service
@Transactional
public class UserServices implements CrudInterface<User>, UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	/**
	 * Method allowing the adjournment of a User by passing the JPA Repositories
	 * @param It takes a data object of type User 
	 * @return returns the instance of this class once the insertion in the database is done 
	 *  @see User
	 */
	@Override
	public User save(User data) {
		// TODO Auto-generated method stub
		return userRepository.save(data);
	}
	/**
	 * Method to update an entity in the database 
	 * @param Take in parameter the new modified entity which overwrites the old one contained in the database 
	 * @return returns the new entity thus modified 
	 * @throws in case of error generates a RessourceNotFoundException translated by the fact that the entity to be modfied is not found 
	 * @see User
	 */
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
	/**
	 * Method to access an entity by its id 
	 * @param Take in parameter the ide of the entity 
	 * @return returns the entity when found in the database 
	 * @throws Throw an exception if the search for the entity fails 
	 */
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
	/**
	 * Method to delete an entity from the database 
	 * @param Take in parameter the ide of the entity to delete 
	 * @throws Throw an exception if the search for the entity fails 
	 * 
	 */

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
	/**
	 * This method returns the list of entities contained in the User 
	 * @return Return a list of Users
	 *  @see User
	 */
	@Override
	public List<User> getAllData() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}
/**
 * Method to access a user in the database by his Username 
 * @return UserDetails , le user une fois trouvé 
 */
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
