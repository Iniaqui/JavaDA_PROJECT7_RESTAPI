package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.execptions.RessourceNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;
/**
 * CRUD method management service for the CurvePoint entity
 * @see CurvePoint
 * @author maure
 *
 */
@Service
@Transactional
public class CurvePointServices  implements CrudInterface<CurvePoint>{
	@Autowired
	CurvePointRepository curvePointRepository;
	/**
	 * Method to update a CurvePoint entity by passing the JPA Repositories
	 * @param It takes a data object of type CurvePoint 
	 * @return returns the instance of this class once the insertion in the database is done 
	 *  @see CurvePoint
	 */
	@Override
	public CurvePoint save(CurvePoint data) {
		// TODO Auto-generated method stub
		return this.curvePointRepository.save(data);
	}
	/**
	 * Method to update a CurvePoint entity in the database 
	 * @param Take as parameter the new modified entity that will overwrite the old one contained in the database 
	 * @return returns the new entity thus modified 
	 * @throws in case of error generates a RessourceNotFoundException translated by the fact that the entity to be modfied is not found 
	 * @see CurvePoint
	 */
	@Override
	public CurvePoint update(CurvePoint data) {
		Optional<CurvePoint> curvePointDB = this.curvePointRepository.findById(data.getId());
		if(curvePointDB.isPresent()) {
			CurvePoint curvePointUpdate= curvePointDB.get();
			curvePointUpdate.setAsOfDate(data.getAsOfDate());
			curvePointUpdate.setCreationDate(data.getCreationDate());
			curvePointUpdate.setCurveId(data.getCurveId());
			curvePointUpdate.setId(data.getId());
			curvePointUpdate.setTerm(data.getTerm());
			curvePointUpdate.setValue(data.getValue());
			
			this.curvePointRepository.save(curvePointUpdate);
			return curvePointUpdate;
		}
		else {
			throw new RessourceNotFoundException("Curve Point not found with id = " +data.getId());
		}
	}
	/**
	 * Method to access an entity by its id 
	 * @param Take in parameter the ide of the entity 
	 * @return returns the entity when found in the database 
	 * @throws Throw an exception if the search for the entity fails 
	 */
	@Override
	public CurvePoint readById(int id) {
		// TODO Auto-generated method stub
		Optional<CurvePoint> curvePointDB = this.curvePointRepository.findById(id);
		if(curvePointDB.isPresent()) {
			return curvePointDB.get();
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
		Optional<CurvePoint> curvePointDB = this.curvePointRepository.findById(id);
		if(curvePointDB.isPresent()) {
			this.curvePointRepository.delete(curvePointDB.get());
		}
		else {
			throw new RessourceNotFoundException("Curve Point not found with id = " +id);
		}
		
	}
	/**
	 * This method returns the list of entities contained in the CurvePoint 
	 * @return Return a list of CurvePoint
	 *  @see CurvePoint
	 */
	@Override
	public List<CurvePoint> getAllData() {
		// TODO Auto-generated method stub
		return this.curvePointRepository.findAll();
	}

}
