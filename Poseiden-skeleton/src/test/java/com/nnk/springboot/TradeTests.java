package com.nnk.springboot;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;


import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTests {

	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	TradeServices tradeServicesTest;
	
	@BeforeAll
	static void setUp() {
		
	}
	
	@Test
	public void test() {
		System.out.println("Bonjour tout le monde ");
	}
	
	@Test
	public void tradeTest() {
		
		
		Trade trade = new Trade("Trade Account", "Type");
	
		// Save
		trade = tradeServicesTest.save(trade);
		assertNotNull(trade.getTradeId());
		assertTrue(trade.getAccount().equals("Trade Account"));
		assertNotNull(trade.getTradeId());

		// Update
		trade.setAccount("Trade Account Update");
		trade = tradeServicesTest.update(trade);
		assertTrue(trade.getAccount().equals("Trade Account Update"));

		// Find
		List<Trade> listResult = tradeServicesTest.getAllData();
		assertTrue(listResult.size() > 0);
		
		Integer id = trade.getTradeId();
		// Find by Id
		Trade findtradeList = tradeServicesTest.readById(id);
		assertNotNull(findtradeList);
		// Delete
		
		tradeServicesTest.deleted(id);
		Trade tradeList = tradeServicesTest.readById(id);
		assertNull(tradeList);
	}
}

