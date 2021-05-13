package com.nnk.springboot.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameServices;
/**
 * Controller handling requests for a RuleName 
 * @author maure
 *
 */
@Controller
public class RuleNameController {
    // TODO: Inject RuleName service
	@Autowired
	RuleNameServices ruleNameServices;
	private final Logger logger= LogManager.getLogger("RuleNameController");
	/**
	 * Method called when /ruleName/list is called to get a list 
	 * @param model
	 * @return a page with the list of entities 
	 */
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // TODO: find all RuleName, add to model
    	logger.info("Start of print the list of RuleName ");
    	List<RuleName> RuleNames = ruleNameServices.getAllData();
    	model.addAttribute("RuleNames", RuleNames);
    	model.addAttribute("userInfo", HomeController.getUserConnect().toString());
        return "ruleName/list";
    }
    /**
     * Method called when /ruleName/add is called to add an entity 
     * @param ruleName
     * @return the form for adding the entity 
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
    	logger.info("Redirect to the page of saving ");
        return "ruleName/add";
    }
    /**
     * Method called when /ruleName/validate is called to add an entity 
     * @param ruleName
     * @return the form for adding the entity with a confirmation
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return RuleName list
    	logger.info("Requete save the RuleName lauch");
    	RuleName ruleNameSaved = ruleNameServices.save(ruleName);
    	boolean saved = false;
    	if(result.hasErrors()) {
    		return "ruleName/add";
    	}
    	if(ruleNameSaved != null) {
    		saved = true;
    		model.addAttribute("value", "RuleName save with sucess");
    	}
    	
    	logger.info("End of Treatment of add RuleName request ");
    	model.addAttribute("saved", saved);
        return "ruleName/add";
    }
    /**
     * Method called when /ruleName/update/{id} is called to modify an entity 
     * @param id corresponding to the id of the entity 
     * @param model
     * @return a form for modfication of the entity 
     */

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form
    	logger.info("Redirect to the update page ");
    	RuleName  ruleNameUpdated = ruleNameServices.readById(id);
    	model.addAttribute("ruleName", ruleNameUpdated);
        return "ruleName/update";
    }
    /**
     * Method called when /ruleName/update/{id} is called to modify an entity 
     * @param id
     * @param ruleName
     * @param result
     * @param model
     * @return return the list of entities once the entity is modified 
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
    	logger.info("Start of request Update RuleName ");
    	if(ruleName.getDescription()!=null && ruleName.getJson() != null && ruleName.getSqlPart() != null && ruleName.getSqlStr() != null) {
    		ruleName.setId(id);
    		ruleNameServices.update(ruleName);
    	}
    	
        return "redirect:/ruleName/list";
    }
    /**
     * Method called when /ruleName/delete/{id} is called to delete an entity 
     * @param id
     * @param model
     * @return a list of available entities 
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
    	logger.info("Start of deleting of RuleName ");
    	ruleNameServices.deleted(id);
        return "redirect:/ruleName/list";
    }
}
