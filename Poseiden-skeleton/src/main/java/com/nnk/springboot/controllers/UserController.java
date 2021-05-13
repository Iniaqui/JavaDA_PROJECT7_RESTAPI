package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
/**
 * Controller handling requests for a User 
 * @author maure
 *
 */
@Controller
public class UserController {
	private final Logger logger = LogManager.getLogger("UserController");
    @Autowired
    private UserRepository userRepository;
/**
 * Method used when /user/list is called to get a list 
 * @param model
 * @return a page with the list of entities 
 */
    @RequestMapping("/user/list")
    public String home(Model model)
    {
    	logger.info("Requets to get list of User");
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }
/**
 * Method used when /user/add is called to add an entity 
 * @param user
 * @return the form for adding the entity 
 */
    @GetMapping("/user/add")
    public String addUser(User user) {
    	logger.info("Redirect to form of Sign Up");
        return "user/add";
    }
    /**
     * Method used when /user/add is called to add an entity 
     * @param user
     * @return the form for adding the entity with a confirmation
     */
    @PostMapping("/user/validate")
    public String validate(@Valid @RequestBody User user, BindingResult result, Model model) {
    	System.out.println("Validation of user");
        if (!result.hasErrors()) {
        	logger.info("Cn start the saving of user ");
           BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
           user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            model.addAttribute("users", userRepository.findAll());
            return "redirect:/user/list";
        }
        else {
        	logger.error("Error in the form");
        	System.out.println("Error in systeme ");
        }
        return "user/add";
    }
  /**
   * Method called when /user/update/{id} is called to modify an entity 
   * @param id corresponding to the id of the entity 
   * @param model
   * @return a form for modfication of the entity 
   */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }
/**
 * Method called when /user/update/{id} is called to modify an entity 
 * @param id
 * @param user
 * @param result
 * @param model
 * @return return the list of entities once the entity is modified 
 */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

       BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }
/**
 * Method called when /user/delete/{id} is called to delete an entity 
 * @param id
 * @param model
 * @return a list of available entities 
 */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }
}
