package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.execptions.RessourceNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
/**
 * CRUD method management service for the Rating entity 
 * @author maure
 *@see Rating
 */
@Service
@Transactional
public class RatingServices implements CrudInterface<Rating> {
	@Autowired
	RatingRepository ratingRepository;
	/**
	 * Method to update a Rating by passing the JPA Repositories
	 * @param It takes a data object of type Rating 
	 * @return returns the instance of this class once the insertion in the database is done 
	 *  @see Rating
	 */
	@Override
	public Rating save(Rating data) {
		// TODO Auto-generated method stub
		return ratingRepository.save(data);
	}
	/**
	 * Method to update an entity in the database 
	 * @param Take as parameter the new modified entity that will overwrite the old one contained in the database 
	 * @return returns the new entity thus modified 
	 * @throws in case of error generates a RessourceNotFoundException translated by the fact that the entity to be modfied is not found 
	 * @see Rating
	 */
	@Override
	public Rating update(Rating data) {
		// TODO Auto-generated method stub
		Optional<Rating> ratingDB = this.ratingRepository.findById(data.getId());
		if(ratingDB.isPresent()) {
			Rating ratingUpdate= ratingDB.get();
			ratingUpdate.setFitchRating(data.getFitchRating());
			ratingUpdate.setId(data.getId());
			ratingUpdate.setMoodysRating(data.getMoodysRating());
			ratingUpdate.setOrderNumber(data.getOrderNumber());
			ratingUpdate.setSandPRating(data.getSandPRating());
			
			this.ratingRepository.save(ratingUpdate);
			return ratingUpdate;
		}
		else {
			throw new RessourceNotFoundException("Rating not found with id = " +data.getId());
		}
		
	}
	/**
	 * Method to access an entity by its id 
	 * @param Take in parameter the ide of the entity 
	 * @return returns the entity when found in the database 
	 * @throws Throw an exception if the search for the entity fails 
	 */
	@Override
	public Rating readById(int id) {
		// TODO Auto-generated method stub
		Optional<Rating> ratingDB = this.ratingRepository.findById(id);
		if(ratingDB.isPresent())
		{
			return ratingDB.get();
		}
		else {
			return null;
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
		Optional<Rating> ratingDB = this.ratingRepository.findById(id);
		if(ratingDB.isPresent())
		{
			this.ratingRepository.delete(ratingDB.get());
		}
		else {
			throw new RessourceNotFoundException("Rating not found with id = " +id);
		}
		
	}
	/**
	 * This method returns the list of entities contained in the Rating 
	 * @return Return a list of Rating
	 *  @see Rating
	 */

	@Override
	public List<Rating> getAllData() {
		// TODO Auto-generated method stub
		return this.ratingRepository.findAll();
	}
	
}
