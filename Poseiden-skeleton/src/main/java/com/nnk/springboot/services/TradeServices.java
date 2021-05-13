package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.execptions.RessourceNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;
/**
 *  It is a class whose role is to execute the CRUD methods linked to the Trade entity
 * @author maure
 *
 */
@Service
@Transactional
public class TradeServices implements CrudInterface<Trade> {
	@Autowired
	TradeRepository tradeRepository;
	/**
	 * Method to postpone a trade by passing the JPA Repositories
	 * @param Elle prends un objet data de type Trade 
	 * @return returns the instance of this class once the insertion in the database is done 
	 *  @see Trade
	 */
	@Override
	public Trade save(Trade data) {
		// TODO Auto-generated method stub
		return this.tradeRepository.save(data);
	}
	/**
	 * Method to update an entity in the database 
	 * @param Take in parameter the new modified entity which overwrites the old one contained in the database 
	 * @return returns the new entity thus modified 
	 * @throws in case of error generates a RessourceNotFoundException translated by the fact that the entity to be modfied is not found 
	 * @see Trade
	 */
	@Override
	public Trade update(Trade data) {
		// TODO Auto-generated method stub
		Optional<Trade> tradeDB = this.tradeRepository.findById(data.getTradeId());
		if(tradeDB.isPresent()) {
			Trade tradeUpdate = tradeDB.get();
			
			tradeUpdate.setAccount(data.getAccount());
			tradeUpdate.setBenchmark(data.getBenchmark());
			tradeUpdate.setBook(data.getBook());
			tradeUpdate.setBuyPrice(data.getBuyPrice());
			tradeUpdate.setBuyQuantity(data.getBuyQuantity());
			tradeUpdate.setCreationDate(data.getCreationDate());
			tradeUpdate.setCreationName(data.getCreationName());
			tradeUpdate.setDealName(data.getDealName());
			tradeUpdate.setDealType(data.getDealType());
			tradeUpdate.setRevisionDate(data.getRevisionDate());
			tradeUpdate.setRevisionName(data.getRevisionName());
			tradeUpdate.setSecurity(data.getSecurity());
			tradeUpdate.setSellPrice(data.getSellPrice());
			tradeUpdate.setSellQuantity(data.getSellQuantity());
			tradeUpdate.setSide(data.getSide());
			tradeUpdate.setSourceListId(data.getSourceListId());
			tradeUpdate.setStatus(data.getStatus());
			tradeUpdate.setTradeDate(data.getTradeDate());
			tradeUpdate.setTradeId(data.getTradeId());
			tradeUpdate.setTrader(data.getTrader());
			tradeUpdate.setType(data.getType());
			 this.tradeRepository.save(tradeUpdate);
			 return tradeUpdate;
		}
		else {
			throw new RessourceNotFoundException("Trade not found with id = " +data.getTradeId());
		}
		
	}
	/**
	 * Method to access an entity by its id 
	 * @param Take in parameter the ide of the entity 
	 * @return returns the entity when found in the database 
	 * @throws Leve une exception en cas d'echec de la recherche de l'entité 
	 */

	@Override
	public Trade readById(int id) {
		// TODO Auto-generated method stub
		Optional<Trade> tradeDB = this.tradeRepository.findById(id);
		if(tradeDB.isPresent()) {
			return tradeDB.get();
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
		Optional<Trade> tradeDB = this.tradeRepository.findById(id);
		if(tradeDB.isPresent()) {
			this.tradeRepository.delete(tradeDB.get() );
		}
		else {
			throw new RessourceNotFoundException("Trade not found with id = " +id);
		}
		
	}
	/**
	 * This method returns the list of entities contained in the Trade 
	 * @return Return a list of Trade
	 *  @see Trade
	 */

	@Override
	public List<Trade> getAllData() {
		// TODO Auto-generated method stub
		return this.tradeRepository.findAll();
	}
	

}
