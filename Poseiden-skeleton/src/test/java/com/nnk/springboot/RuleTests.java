package com.nnk.springboot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleTests {

	@Autowired
	private RuleNameRepository ruleNameRepository;
	@Autowired
	RuleNameServices ruleNameServices;

	@Test
	public void ruleTest() {
		RuleName rule = new RuleName("Rule Name");

		// Save
		rule = ruleNameServices.save(rule);
		assertNotNull(rule.getId());
		assertTrue(rule.getName().equals("Rule Name"));

		// Update
		rule.setName("Rule Name Update");
		rule = ruleNameServices.update(rule);
		assertTrue(rule.getName().equals("Rule Name Update"));

		// Find
		List<RuleName> listResult = ruleNameServices.getAllData();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rule.getId();
		ruleNameServices.deleted(id);
		Optional<RuleName> ruleList = ruleNameRepository.findById(id);
		assertFalse(ruleList.isPresent());
	}
}
