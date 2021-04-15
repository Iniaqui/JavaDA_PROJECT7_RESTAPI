package com.nnk.springboot;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
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
	CurvePointServices curvePointService;
	
	
	@BeforeAll
	static void setUp() {
		
	}
	
	
	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint(10, 10d);

		// Save
		CurvePoint curvePointSave = curvePointService.save(curvePoint);
		assertNotNull(curvePointSave.getId());
		System.out.println(curvePointSave.getId());
		assertTrue(curvePointSave.getCurveId() == 10);

		// Update
		curvePointSave.setCurveId(20);
		CurvePoint curvePointUpdate = curvePointService.update(curvePointSave);
		assertTrue(curvePointUpdate.getCurveId() == 20);

		// Find
		List<CurvePoint> listResult = curvePointService.getAllData();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = curvePointUpdate.getId();
		curvePointService.deleted(id);
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		assertFalse(curvePointList.isPresent());
	}

}
