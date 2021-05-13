package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
/**
 * Class allowing the connection 
 * @author maure
 *
 */
@Controller
@RequestMapping("app")
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    private final Logger logger = LogManager.getLogger("LoginController");
    @GetMapping("login")
    public ModelAndView login(User user) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", user);
        mav.setViewName("login");
        logger.info("Renvoie vers la page de login");
        return mav;
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
    
    @GetMapping("/signUp")
    public ModelAndView signUp(User user) {
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("user", user);
    	mav.setViewName("signUp");
    	return mav;
    }
}
