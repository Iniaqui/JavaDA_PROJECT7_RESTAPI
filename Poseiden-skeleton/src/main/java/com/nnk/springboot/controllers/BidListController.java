package com.nnk.springboot.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.BidListService;

/**
 * Controller in charge of processing requests concerning a BidList 
 * @author maure
 *
 */
@Controller
public class BidListController {
    // TODO: Inject Bid service
	private final Logger logger = LogManager.getLogger("BidListController");
	@Autowired
	BidListService bidListService;
	/**
	 * Method called when /bidList/list is called to get a list 
	 * @param model
	 * @return a url with the list of entities 
	 */
    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
    	logger.info("Demmarage de l'affichage des Bid List");
    	List<BidList> listeBidList = bidListService.getAllData();
    		model.addAttribute("Bids", listeBidList);
    		ServletRequestAttributes  s = (ServletRequestAttributes )RequestContextHolder.getRequestAttributes();
    		HttpServletRequest r = s.getRequest();
    		r.setAttribute("remoteUser","RemoteUser" );
    		r.setAttribute("User", "Tesxt");
    		logger.info("GetRemote  : " +  HomeController.getUserConnect().toString());
    		model.addAttribute("userInfo", HomeController.getUserConnect().toString());
    		logger.info("Fin de traitement  de l'affichage des Bid List");
        return "bidList/list";
    }
    /**
     * Method called when /bidList/add is called to add an entity 
     * @param bid
     * @return a url to a form for adding the entity 
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid , Model model) {
    	boolean saved = false ;
    	logger.info("Redirect to the page of saving ");
    	model.addAttribute("saved", saved);
        return "bidList/add";
    }
    /**
     * Method called when /bidList/validate is called to add an entity 
     * @param bid
     * @return a url to the form to add an entity with a bidList/add confirmation
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid @RequestBody BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
    	
    	if(result.hasErrors()) {
    		logger.error("Validation of Bid List Failed");
    		return "bidList/add";
    		
    	}
    	else {
    		boolean saved = false ;
        	logger.info("Requete save the bid list lauch");
        	BidList bidList = bidListService.save(bid);
        	if(bidList!=null) {
        		saved=true;
        		logger.info("Success of adding BidList");
        		model.addAttribute("value", "BidList add with success");
        		
        	}
        	model.addAttribute("saved", saved);
        	logger.info("End of Treatment of add BidList request ");
    	}
    	
        return "bidList/add";
    }
    /**
     * Method called when /bidList/update/{id} is called to modify an entity
     * @param id corresponding to id of the entity 
     * @param model
     * @return a url to a modfication form of the entity 
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
    	logger.info("Redirect to the update page ");
    	BidList bidList = bidListService.readById(id);
    	model.addAttribute("bidList", bidList);
    	
        return "bidList/update";
    }
    /**
     * Method called when /bidList/update/{id} is called to modify an entity 
     * @param id
     * @param bidList
     * @param result
     * @param model
     * @return return the list of entities once the entity is modified 
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
    	logger.info("Start of request Update Bid List ");
    	if (bidList.getAccount() != null && bidList.getType()!= null && bidList.getBidQuantity() != null) {
    		bidList.setBidListId(id);
    		bidListService.update(bidList);
    	}
    	else {
    		logger.error("Failed to update BidList");
    		model.addAttribute("Updated","A required field is blank");
    		
    	}
    	logger.info("rediection in to list of BidList");
        return "redirect:/bidList/list";
    }
    /**
     * Method called when /bidList/delete/{id} is called to delete an entity 
     * @param id
     * @param model
     * @return a list of available entities 
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
    	logger.info("Start of deleting of BidList ");
    	bidListService.deleted(id);
    	logger.info("End of treatment and Succes of deleting ");
        return "redirect:/bidList/list";
    }
}
