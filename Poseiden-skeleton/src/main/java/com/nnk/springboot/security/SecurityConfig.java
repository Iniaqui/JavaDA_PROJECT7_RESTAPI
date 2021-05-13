package com.nnk.springboot.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.nnk.springboot.services.UserServices;
/**
 * Security configuration class, will be scanned by spring boot when launching the app 
 * 
 * @author maure
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserServices userDetailsService;
	  /**
	   * Method to establish a global configuration of how users will be authenticated 
	   * Uses a dataSource that contains the list of authorized users 
	   * @param auth
	   * @param dataSource
	   * @throws Exception
	   */
	 @Autowired
	 public void globalConfig(AuthenticationManagerBuilder auth,DataSource dataSource) throws Exception{
		 auth.jdbcAuthentication()
		 		.dataSource(dataSource)
		 		.usersByUsernameQuery("select username as principal,password as credentials, true from users where username = ? ")
		 		.authoritiesByUsernameQuery("select username as principal , role as role from users where username = ?");
		 
		 		
	 }
	 /**
	  * Method to manage the accessibility of pages 
	  * @param Take an HttpSecurity that will scan the user's request 
	  */
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/user/add").permitAll()
				.antMatchers("/user/validate").permitAll()
				.anyRequest()
						.authenticated()
							.and()
			
			.formLogin()
				.loginPage("/app/login")
					.permitAll()
					.defaultSuccessUrl("/")
					.failureUrl("/app/error")
			    .and()
			    .logout()
			    	.logoutUrl("/app-logout")
			    	.logoutSuccessUrl("/app/login")
			    	.and()
		    	.oauth2Login();
		    
	}
	/**
	 * Static method to generate a Bcrypt instance that takes care of encrypting the information 
	 * @return
	 */
	  @Bean
	    public static BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}
