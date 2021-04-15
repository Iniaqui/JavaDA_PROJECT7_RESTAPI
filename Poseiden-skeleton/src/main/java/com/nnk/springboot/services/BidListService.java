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


@Service
@Transactional
public class BidListService implements CrudInterface<BidList> {
	@Autowired
	BidListRepository bidListRepository;

	@Override
	public BidList save(BidList data) {
		// TODO Auto-generated method stub
		return bidListRepository.save(data);
	}

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

	@Override
	public BidList readById(int id) {
		// TODO Auto-generated method stub
		
		Optional <BidList> bidListDb= this.bidListRepository.findById(id);
		if(bidListDb.isPresent()) {
			return bidListDb.get();
		}
		else {
			throw new RessourceNotFoundException("Record not found with id = " +id);
		}
		
	}

	@Override
	public BidList readByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

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

	@Override
	public List<BidList> getAllData() {
		
		// TODO Auto-generated method stub
		return this.bidListRepository.findAll();
	}
}
