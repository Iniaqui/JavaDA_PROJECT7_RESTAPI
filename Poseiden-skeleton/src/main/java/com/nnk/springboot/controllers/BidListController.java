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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;


@Controller
public class BidListController {
    // TODO: Inject Bid service
	private final Logger logger = LogManager.getLogger("BidListController");
	@Autowired
	BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
    	logger.info("Demmarage de l'affichage des Bid List");
    	List<BidList> listeBidList = bidListService.getAllData();
    		model.addAttribute("Bids", listeBidList);
    		logger.info("Fin de traitement  de l'affichage des Bid List");
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid , Model model) {
    	boolean saved = false ;
    	logger.info("Redirect to the page of saving ");
    	model.addAttribute("saved", saved);
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
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

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
    	logger.info("Redirect to the update page ");
    	BidList bidList = bidListService.readById(id);
    	model.addAttribute("bidList", bidList);
    	
        return "bidList/update";
    }

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

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
    	logger.info("Start of deleting of BidList ");
    	bidListService.deleted(id);
    	logger.info("End of treatment and Succes of deleting ");
        return "redirect:/bidList/list";
    }
}
