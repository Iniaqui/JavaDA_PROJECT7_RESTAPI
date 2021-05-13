package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.CurvePoint;
/**
 *  Repository inheriting from the JpaRepository class to process information with the database 
 * @author maure
 *
 */
@Repository
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
