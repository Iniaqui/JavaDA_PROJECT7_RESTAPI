package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Repository inheriting from the JpaRepository class to process information with the database 
 * @author maure
 *
 */
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
