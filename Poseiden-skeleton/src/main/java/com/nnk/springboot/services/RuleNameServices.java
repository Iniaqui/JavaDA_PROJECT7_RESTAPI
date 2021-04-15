package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.execptions.RessourceNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
@Transactional
public class RuleNameServices implements CrudInterface<RuleName>{
	
	@Autowired
	RuleNameRepository ruleNameRepository;

	@Override
	public RuleName save(RuleName data) {
		// TODO Auto-generated method stub
		return this.ruleNameRepository.save(data);
	}

	@Override
	public RuleName update(RuleName data) {
		// TODO Auto-generated method stub
		Optional<RuleName> ruleName= this.ruleNameRepository.findById(data.getId());
		if(ruleName.isPresent()) {
			RuleName ruleNameUpdate = ruleName.get();
			ruleNameUpdate.setDescription(data.getDescription());
			ruleNameUpdate.setId(data.getId());
			ruleNameUpdate.setJson(data.getJson());
			ruleNameUpdate.setName(data.getName());
			ruleNameUpdate.setSqlPart(data.getSqlPart());
			ruleNameUpdate.setSqlStr(data.getSqlStr());
			
			this.ruleNameRepository.save(ruleNameUpdate);
			return ruleNameUpdate;
		}
		else {
			throw new RessourceNotFoundException("Record not found with id = " +data.getId());
		}
	}

	@Override
	public RuleName readById(int id) {
		// TODO Auto-generated method stub
		Optional<RuleName> ruleName= this.ruleNameRepository.findById(id);
		if(ruleName.isPresent()) {
			return ruleName.get();
		}
		else {
			throw new RessourceNotFoundException("Record not found with id = " +id);
		}
	}

	@Override
	public RuleName readByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleted(int id) {
		// TODO Auto-generated method stub
		Optional<RuleName> ruleName= this.ruleNameRepository.findById(id);
		if(ruleName.isPresent()) {
			this.ruleNameRepository.delete(ruleName.get());
		}
		else {
			throw new RessourceNotFoundException("Record not found with id = " +id);
		}
		
	}

	@Override
	public List<RuleName> getAllData() {
		// TODO Auto-generated method stub
		return this.ruleNameRepository.findAll();
	}
	

}
