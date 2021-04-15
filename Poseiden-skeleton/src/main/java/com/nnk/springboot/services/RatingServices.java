package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.execptions.RessourceNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;

@Service
@Transactional
public class RatingServices implements CrudInterface<Rating> {
	@Autowired
	RatingRepository ratingRepository;

	@Override
	public Rating save(Rating data) {
		// TODO Auto-generated method stub
		return ratingRepository.save(data);
	}

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

	@Override
	public Rating readById(int id) {
		// TODO Auto-generated method stub
		Optional<Rating> ratingDB = this.ratingRepository.findById(id);
		if(ratingDB.isPresent())
		{
			return ratingDB.get();
		}
		else {
			throw new RessourceNotFoundException("Rating not found with id = " +id);
		}
	}

	@Override
	public Rating readByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

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

	@Override
	public List<Rating> getAllData() {
		// TODO Auto-generated method stub
		return this.ratingRepository.findAll();
	}
	
}
