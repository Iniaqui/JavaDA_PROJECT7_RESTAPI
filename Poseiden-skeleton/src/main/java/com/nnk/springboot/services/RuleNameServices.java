package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.execptions.RessourceNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
/**
 *  Il s'agt d'une classe ayant pour role d'excuter les méthode CRUD lié à l'entité RuleName
 * @author maure
 *
 */
@Service
@Transactional
public class RuleNameServices implements CrudInterface<RuleName>{
	
	@Autowired
	RuleNameRepository ruleNameRepository;
	/**
	 * Methode permettant l'ajour d'un RuleName en passant le JPA Repositories
	 * @param Elle prends un objet data de type RuleName 
	 * @return retourne l'instance de cette classe une fois l'insertion dans la base de donnée effectué 
	 *  @see RuleName
	 */
	@Override
	public RuleName save(RuleName data) {
		// TODO Auto-generated method stub
		return this.ruleNameRepository.save(data);
	}
	/**
	 * Methode permttant de mettre à jour une entité dans la base de donnée 
	 * @param Prends en parametre la nouvelle entité modifié qui écrasear l'ancienne contenue dans la base de donnée 
	 * @return retourne la nouvelle entité ainsi modifié 
	 * @throws en cas d'erreur génère une RessourceNotFoundException traduit par le fait que l'entité à modfié est introuvable 
	 * @see RuleName
	 */
	@Override
	public RuleName update(RuleName data) {
		// TODO Auto-generated method stub
		Optional<RuleName> ruleName= this.ruleNameRepository.findById(data.getId());
		if(ruleName.isPresent()) {
			RuleName ruleNameUpdate = ruleName.get();
			ruleNameUpdate.setDescription(data.getDescription());
			ruleNameUpdate.setId(data.getId());
			ruleNameUpdate.setJson(data.getJson());
			ruleNameUpdate.setName(data.getName());
			ruleNameUpdate.setSqlPart(data.getSqlPart());
			ruleNameUpdate.setSqlStr(data.getSqlStr());
			
			this.ruleNameRepository.save(ruleNameUpdate);
			return ruleNameUpdate;
		}
		else {
			throw new RessourceNotFoundException("Record not found with id = " +data.getId());
		}
	}
	/**
	 * Methode permettant d'accedé à une entité par son id 
	 * @param Prends en parametre l'ide de l'entité 
	 * @return retourne l'entité une fois trouvé dans la base de donnée 
	 * @throws Leve une exception en cas d'echec de la recherche de l'entité 
	 */

	@Override
	public RuleName readById(int id) {
		// TODO Auto-generated method stub
		Optional<RuleName> ruleName= this.ruleNameRepository.findById(id);
		if(ruleName.isPresent()) {
			return ruleName.get();
		}
		else {
			return null;
		}
	}
	/**
	 * Methode permettant de supprimer da la base de donnée un entité 
	 * @param Prends en parametre l'ide de l'entité à supprimer 
	 * @throws Leve une exception en cas d'echec de la recherche de l'entité 
	 * 
	 */
	@Override
	public void deleted(int id) {
		// TODO Auto-generated method stub
		Optional<RuleName> ruleName= this.ruleNameRepository.findById(id);
		if(ruleName.isPresent()) {
			this.ruleNameRepository.delete(ruleName.get());
		}
		else {
			throw new RessourceNotFoundException("Record not found with id = " +id);
		}
		
	}
	/**
	 * Cette méthode retourne la liste des entité contenue dans la RuleName 
	 * @return Return une liste de RuleName
	 *  @see RuleName
	 */

	@Override
	public List<RuleName> getAllData() {
		// TODO Auto-generated method stub
		return this.ruleNameRepository.findAll();
	}
	

}
