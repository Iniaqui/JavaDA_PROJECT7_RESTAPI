package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.execptions.RessourceNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;

@Service
@Transactional
public class TradeServices implements CrudInterface<Trade> {
	@Autowired
	TradeRepository tradeRepository;

	@Override
	public Trade save(Trade data) {
		// TODO Auto-generated method stub
		return this.tradeRepository.save(data);
	}

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

	@Override
	public Trade readById(int id) {
		// TODO Auto-generated method stub
		Optional<Trade> tradeDB = this.tradeRepository.findById(id);
		if(tradeDB.isPresent()) {
			return tradeDB.get();
		}
		else {
			throw new RessourceNotFoundException("Trade not found with id = " +id);
		}
	}

	@Override
	public Trade readByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

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

	@Override
	public List<Trade> getAllData() {
		// TODO Auto-generated method stub
		return this.tradeRepository.findAll();
	}
	

}
