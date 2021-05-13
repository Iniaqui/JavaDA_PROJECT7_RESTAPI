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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingServices;
/**
 * Controller handling requests for a Rate 
 * @author maure
 *
 */
@Controller
public class RatingController {
    // TODO: Inject Rating service
	@Autowired
	RatingServices ratingServices;
	private final Logger logger= LogManager.getLogger("RatingController");
	/**
	 * Method used when the /rating/list query is called to get a list 
	 * @param model
	 * @return a page with the list of entities 
	 */
    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        // TODO: find all Rating, add to model
    	logger.info("Start of print the list of Rating ");
    	List<Rating> listeRating = ratingServices.getAllData();
    	model.addAttribute("listeRating", listeRating);
    	model.addAttribute("userInfo", HomeController.getUserConnect().toString());
        return "rating/list";
    }
    /**
     * Method used when /rating/add is called to add an entity 
     * @param rating
     * @return the form for adding the entity 
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
    	logger.info("Redirect to the page of saving ");
        return "rating/add";
    }
    /**
     * Method used when /rating/validate is called to add an entity 
     * @param rating
     * @return the form for adding the entity with a confirmation
     */

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Rating list
    	boolean isSaved = false;
    	if(result.hasErrors()) {
    		  return "rating/add";
    	}
    	logger.info("Requete save the Rating lauch");
    	Rating ratingSaved = ratingServices.save(rating);
    	if(ratingSaved != null) {
    		isSaved = true;
    		model.addAttribute("value", "Rating add with success");
    	}
    	model.addAttribute("saved", isSaved);
    	logger.info("End of Treatment of add Rating request ");
        return "rating/add";
    }
    /**
     * Method called when /rating/update/{id} is called to modify an entity 
     * @param id corresponding to the id of the entity 
     * @param model
     * @return a form for modfication of the entity 
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form
    	logger.info("Redirect to the update page ");
    	Rating rating = ratingServices.readById(id);
    	model.addAttribute("rating", rating);
        return "rating/update";
    }
    /**
     * Method called when /rating/update/{id} is called to modify an entity 
     * @param id
     * @param rating
     * @param result
     * @param model
     * @return return the list of entities once the entity is modified 
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Rating and return Rating list
    	logger.info("Start of request Update Rating ");
    	if(rating.getFitchRating() != null && rating.getMoodysRating() != null && rating.getOrderNumber() != null && rating.getSandPRating() !=  null) {
    		rating.setId(id);
        	ratingServices.update(rating);
    	}
        return "redirect:/rating/list";
    }
    /**
     * Method called when /rating/delete/{id} is called to delete an entity 
     * @param id
     * @param model
     * @return a list of available entities 
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
    	logger.info("Start of deleting of Rating ");
    	ratingServices.deleted(id);
        return "redirect:/rating/list";
    }
}
