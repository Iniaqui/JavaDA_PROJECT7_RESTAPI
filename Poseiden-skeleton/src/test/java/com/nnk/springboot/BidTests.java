package com.nnk.springboot;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BidTests {

	@Autowired
	private BidListRepository bidListRepository;
	@Autowired 
	BidListService bidListeService;
	static BidList bid;

	@BeforeAll
	static void setUp() {
		
	}
	
	@Test
	public void bidListTest() {
		 bid = new BidList("Account Test", "Type Test", 10d);

		// Save
		bid =bidListeService.save(bid);
		assertNotNull(bid);
		assertNotNull(bid.getBidListId());
		assertEquals(bid.getBidQuantity(), 10d, 10d);
		

		// Update
		bid.setBidQuantity(20d);
		bid = bidListeService.update(bid);
		assertEquals(bid.getBidQuantity(), 20d, 20d);

		// Find
		List<BidList> listResult = bidListeService.getAllData();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = bid.getBidListId();
		bidListeService.deleted(id);
		Optional<BidList> bidList = bidListRepository.findById(id);
		assertFalse(bidList.isPresent());
	}
	
	
}
