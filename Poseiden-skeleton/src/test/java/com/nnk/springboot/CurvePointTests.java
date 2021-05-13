package com.nnk.springboot;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepository;
	@Autowired
	CurvePointServices curPointServices;
	CurvePoint curvePoint = new CurvePoint(10, 10d);
	@Test
	public void curvePointTest() {
		

		// Save
		curvePoint = curPointServices.save(curvePoint);
		Assert.assertNotNull(curvePoint.getId());
		Assert.assertTrue(curvePoint.getCurveId() == 10);

		// Update
		curvePoint.setCurveId(20);
		curvePoint = curPointServices.update(curvePoint);
		Assert.assertTrue(curvePoint.getCurveId() == 20);

		// Find
		List<CurvePoint> listResult = curPointServices.getAllData();
		Assert.assertTrue(listResult.size() > 0);
		
		// FInd by id 
		Integer id = curvePoint.getId();
		CurvePoint idcurvePointList = curPointServices.readById(id);
		Assert.assertNotNull(idcurvePointList);
		// Delete
		
		curPointServices.deleted(curvePoint.getId());
		CurvePoint curvePointList = curPointServices.readById(id);
		Assert.assertNull(curvePointList);
		
	}
	

}
