package com.nnk.springboot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingTests {

	@Autowired
	private RatingRepository ratingRepository;
	@Autowired
	RatingServices ratingServices;

	@Test
	public void ratingTest() {
		Rating rating = new Rating(10);

		// Save
		rating = ratingServices.save(rating);
		assertNotNull(rating.getId());
		assertTrue(rating.getOrderNumber() == 10);

		// Update
		rating.setOrderNumber(20);
		rating = ratingServices.update(rating);
		assertTrue(rating.getOrderNumber() == 20);

		// Find
		List<Rating> listResult = ratingServices.getAllData();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rating.getId();
		ratingServices.deleted(id);;
		Optional<Rating> ratingList = ratingRepository.findById(id);
		assertFalse(ratingList.isPresent());
	}
}
