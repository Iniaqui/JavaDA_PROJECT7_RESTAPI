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
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointServices;

@Controller
public class CurveController {
    // TODO: Inject Curve Point service
	@Autowired
	CurvePointServices curvePointService;
	private final Logger logger= LogManager.getLogger("CurveController");

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model
    	logger.info("Start of print the list of Curver ");
    	List<CurvePoint> curvePoint = curvePointService.getAllData();
    	model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid,Model model) {
    	boolean saved = false ;
    	logger.info("Redirect to the page of saving ");
    	model.addAttribute("saved", saved);
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
    	boolean saved = false ;
    	if(result.hasErrors()) {
    		return "curvePoint/add";
    	}
    	logger.info("Requete save the CurvePoint lauch");
    	CurvePoint curvePointSaved = curvePointService.save(curvePoint);
    	if(curvePointSaved!=null) {
    		saved=true;
    		
    		model.addAttribute("value", "CurvePoint add with success");
    		
    	}
    	model.addAttribute("saved", saved);
    	model.addAttribute("curvePoint", curvePointSaved);
    	logger.info("End of Treatment of add CurvePoint request ");
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
    	CurvePoint curvePoint = curvePointService.readById(id);
    	logger.info("Redirect to the update page ");
    	model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
    	logger.info("Start of request Update CurvePoint ");
    	curvePointService.update(curvePoint);
    	logger.info("rediection in to list of CurvePoint");
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
    	logger.info("Start of deleting of CurvePoint ");
    	curvePointService.deleted(id);
        return "redirect:/curvePoint/list";
    }
}
