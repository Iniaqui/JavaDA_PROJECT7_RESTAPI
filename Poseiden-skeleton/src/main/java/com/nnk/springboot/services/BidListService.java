package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.execptions.RessourceNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;

import ch.qos.logback.classic.Logger;

/**
 * It is a class whose role is to execute the CRUD methods linked to the BidList entity
 * @author maure
 *
 */

@Service
@Transactional
public class BidListService implements CrudInterface<BidList> {
	@Autowired
	BidListRepository bidListRepository;
/**
 * Method to update a BidList by passing the JPA Repositories
 * @param It takes a data object of type BidList 
 * @return returns the instance of this class once the insertion in the database is done 
 *  @see BidList
 */
	@Override
	public BidList save(BidList data) {
		// TODO Auto-generated method stub
		return bidListRepository.save(data);
	}
/**
 * Method to update an entity in the database 
 * @param Take as parameter the new modified entity that will overwrite the old one contained in the database 
 * @return returns the new entity thus modified 
 * @throws in case of error generates a RessourceNotFoundException translated by the fact that the entity to be modfied is not found 
 * @see BidList
 */

	@Override
	public BidList update(BidList data) {
		// TODO Auto-generated method stub
		Optional <BidList> bidListDb= this.bidListRepository.findById(data.getBidListId());
		if(bidListDb.isPresent()) {
			BidList bidListUpdate= bidListDb.get();
			bidListUpdate.setBidListId(data.getBidListId());
			bidListUpdate.setAccount(data.getAccount());
			bidListUpdate.setAsk(data.getAsk());
			bidListUpdate.setAskQuantity(data.getAskQuantity());
			bidListUpdate.setBenchmark(data.getBenchmark());
			bidListUpdate.setBid(data.getBid());
			bidListUpdate.setBidListDate(data.getBidListDate());
			bidListUpdate.setBidQuantity(data.getBidQuantity());
			bidListUpdate.setBook(data.getBook());
			bidListUpdate.setCommentary(data.getCommentary());
			bidListUpdate.setCreationDate(data.getCreationDate());
			bidListUpdate.setCreationName(data.getCreationName());
			bidListUpdate.setDealName(data.getDealName());
			bidListUpdate.setDealType(data.getDealType());
			bidListUpdate.setRevisionDate(data.getRevisionDate());
			bidListUpdate.setRevisionName(data.getRevisionName());
			bidListUpdate.setSecurity(data.getSecurity());
			bidListUpdate.setSide(data.getSide());
			bidListUpdate.setSourceListId(data.getSourceListId());
			bidListUpdate.setStatus(data.getStatus());
			bidListUpdate.setTrader(data.getTrader());
			bidListUpdate.setType(data.getType());
			
			this.bidListRepository.save(bidListUpdate);
			
			return bidListUpdate;
		}
		else {
			throw new RessourceNotFoundException("Record not found with id = " +data.getBidListId());
		}
		
	}
	
	/**
	 * Method to access an entity by its id 
	 * @param Take in parameter the ide of the entity 
	 * @return returns the entity when found in the database 
	 * @throws Throw an exception if the search for the entity fails 
	 */

	@Override
	public BidList readById(int id) {
		// TODO Auto-generated method stub
		
		Optional <BidList> bidListDb= this.bidListRepository.findById(id);
		if(bidListDb.isPresent()) {
			return bidListDb.get();
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
		
		Optional <BidList> bidListDb= this.bidListRepository.findById(id);
		if(bidListDb.isPresent()) {
			this.bidListRepository.delete(bidListDb.get());
		}
		else {
			throw new RessourceNotFoundException("Record not found with id = " +id);
		}
	}
/**
 * This method returns the list of entities contained in the BidList 
 * @return Return a list of BidList
 *  @see BidList
 */
	@Override
	public List<BidList> getAllData() {
		
		// TODO Auto-generated method stub
		return this.bidListRepository.findAll();
	}
}
