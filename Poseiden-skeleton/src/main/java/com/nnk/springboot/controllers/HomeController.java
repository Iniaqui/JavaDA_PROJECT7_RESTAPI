package com.nnk.springboot.controllers;

import java.security.Principal;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserServices;

@Controller
public class HomeController
{
	private static  StringBuffer userInfo;// Ce objet va stokeer les information de l'utilisateur qui est connecté 
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	@Autowired
	UserServices userServices;
	 private final Logger logger = LogManager.getLogger("HomeController");
	@RequestMapping("/")
	public String home(Model model,Principal user)
	{
		userInfo = new StringBuffer();
		
		if(user instanceof UsernamePasswordAuthenticationToken) {
			logger.info("AUthentification normal");
			userInfo.append(getUsernamePasswordLoginInfo(user));
		}
		else if( user instanceof OAuth2AuthenticationToken) {
			logger.info("AUthentification avec Token");
			userInfo.append(getOauth2LoginInfo(user));
		}
		model.addAttribute("User",userInfo.toString());
		
		
		if(userInfo==null) {
		
			logger.info("UserInfo is null");
		}
		else {
			logger.info("Demande d'authentification venant de la part de " + userInfo.toString());
		}
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/bidList/list";
	}
	private StringBuffer getOauth2LoginInfo(Principal user){// Cette méthode 
	    StringBuffer protectedInfo = new StringBuffer();
	    OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
	    OAuth2AuthorizedClient authClient = this.authorizedClientService.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());
	    if(authToken.isAuthenticated()) {
	    	logger.info("Lancement de la méthode getOauth2LoginInfo");
	    	Map<String, Object> userAttributes = ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes();
	    	String userToken = authClient.getAccessToken().getTokenValue();
	    	logger.info("Le user connecté est " + userAttributes.get("login"));
	    	protectedInfo.append(userAttributes.get("login"));
	    	
	    }
	    else {
	    	protectedInfo.append("NA");
	    }
	    return protectedInfo;
	}
	private StringBuffer getUsernamePasswordLoginInfo(Principal user) {
		
		StringBuffer userNameInfo = new StringBuffer();
		UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);// Authentifie l'utilisateur et renvoir un token 
		if(token.isAuthenticated()) {// Dans le cas ou le token est authentifié 
			User u = (User) userServices.loadUserByUsername(token.getName());//u recupere les information de l'utilisation grace au token , contenant les informations principale 
			userNameInfo.append("name : "+ u.getUsername());
		}
		else {
			userNameInfo.append("NA");
		}
		return userNameInfo;
	}
	public static  String getUserConnect() {
		
		return userInfo.toString();
	}
}
