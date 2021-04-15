package com.nnk.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserServices;


public class AppAuthProvider extends DaoAuthenticationProvider {
	@Autowired
	UserServices userDetailsServices;
	@Autowired 
	UserRepository userRepository;
	@Override
	public Authentication authenticate(Authentication autentication) throws AuthenticationException{
		//Cette classe  methode authentifie le user et retourn un token valide qui sera ensuite placer dans le header de la requete
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) autentication;//Permet d'obtenir les information d'authentification d'un user 
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		if(userRepository == null) {
			System.out.println("NULL");
		}
		UserDetails user = userRepository.getByUserName(username);
		if(user == null) {
			throw new BadCredentialsException("Usernme or Password doesn't match"+auth.getPrincipal());
		}
		return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
	}
	
	 @Override
	    public boolean supports(Class<?> authentication) {
	        return true;

	    }

}
