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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeServices;
/**
 * Controller dealing with requests concerning a Trade 
 * @author maure
 *
 */
@Controller
public class TradeController {
    // TODO: Inject Trade service
	@Autowired
	TradeServices tradeServices;
	private final Logger logger= LogManager.getLogger("TradeController");
	/**
	 * Method called when /user/list is called to get a list
	 * @param model
	 * @return a page with the list of entities 
	 */
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        // TODO: find all Trade, add to model
    	logger.info("Start of print the list of Trade ");
    	List<Trade> Trades = tradeServices.getAllData();
    	model.addAttribute("userInfo", HomeController.getUserConnect().toString());
    	model.addAttribute("Trades", Trades);
        return "trade/list";
    }
    /**
     * Method used when /user/add is called to add an entity 
     * @param trade
     * @return the form for adding the entity 
     */
    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
    	logger.info("Redirect to the page of saving ");
        return "trade/add";
    }
    /**
     * Method used when /user/add is called to add an entity 
     * @param trade
     * @return the form for adding the entity with a confirmation
     */

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Trade list
    	if(result.hasErrors()) {
    		 return "trade/add";
    	}
    	logger.info("Requete save the Trade lauch");
    	boolean saved = false ; 
    	Trade tradeSaved = tradeServices.save(trade);
    	if(tradeSaved != null) {
    		saved = true;
    	}
    	logger.info("End of Treatment of add Trade request ");
    	model.addAttribute("saved", saved);
        return "trade/add";
    }
    /**
     * Method called when /user/update/{id} is called to modify an entity 
     * @param id corresponding to the id of the entity 
     * @param model
     * @return a form for modfication of the entity 
     */

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form
    	logger.info("Redirect to the update page ");
    	model.addAttribute("trade", tradeServices.readById(id));
        return "trade/update";
    }
    /**
     * Method called when /user/update/{id} is called to modify an entity 
     * @param id
     * @param trade
     * @param result
     * @param model
     * @return return the list of entities once the entity is modified 
     */

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Trade and return Trade list
    	logger.info("Start of request Update Trade ");
    	if(trade.getAccount()!= null  && trade.getBuyQuantity()!=null && trade.getBuyQuantity()!= null && trade.getType()!= null) {
    		trade.setTradeId(id);
    		tradeServices.update(trade);
    	}
        return "redirect:/trade/list";
    }
    /**
     * Method called when /user/delete/{id} is called to delete an entity 
     * @param id
     * @param model
     * @return a list of available entities 
     */

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
    	logger.info("Start of deleting of Trade ");
    	tradeServices.deleted(id);
        return "redirect:/trade/list";
    }
}
