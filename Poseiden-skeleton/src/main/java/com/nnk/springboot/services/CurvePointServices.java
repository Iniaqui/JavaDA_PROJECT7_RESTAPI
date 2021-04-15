package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.execptions.RessourceNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
@Transactional
public class CurvePointServices  implements CrudInterface<CurvePoint>{
	@Autowired
	CurvePointRepository curvePointRepository;

	@Override
	public CurvePoint save(CurvePoint data) {
		// TODO Auto-generated method stub
		return this.curvePointRepository.save(data);
	}

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

	@Override
	public CurvePoint readById(int id) {
		// TODO Auto-generated method stub
		Optional<CurvePoint> curvePointDB = this.curvePointRepository.findById(id);
		if(curvePointDB.isPresent()) {
			return curvePointDB.get();
		}
		else {
			throw new RessourceNotFoundException("Record not found with id = " +id);
		}
	}

	@Override
	public CurvePoint readByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

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

	@Override
	public List<CurvePoint> getAllData() {
		// TODO Auto-generated method stub
		return this.curvePointRepository.findAll();
	}

}
