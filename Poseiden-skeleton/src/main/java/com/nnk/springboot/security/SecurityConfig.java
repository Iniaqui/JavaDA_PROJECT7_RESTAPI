package com.nnk.springboot.security;

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

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserServices userDetailsService;
	 
	 @Override
	 public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		 authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());//Permet de creer l'authentification avec la base de données
	 }
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.csrf()
			.disable()
			.exceptionHandling()
			.authenticationEntryPoint(new Http403ForbiddenEntryPoint() {
            })
			.and()
			.authenticationProvider(getProvider())
			.formLogin()
			.loginPage("/app/login")
			.loginProcessingUrl("/login")
			.successHandler(new AuthentificationLoginSuccessHandler())
			.failureHandler(new SimpleUrlAuthenticationFailureHandler())
			.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
			.invalidateHttpSession(true)
			.and()
			.authorizeRequests()
			.antMatchers("/app/login").permitAll()
			.antMatchers("/logout").permitAll()
			.anyRequest().authenticated();
			
			
			
			
		
	}
	

	private AuthenticationProvider getProvider() {
		// TODO Auto-generated method stub
		AppAuthProvider provider = new AppAuthProvider();
        provider.setUserDetailsService(userDetailsService);
        return provider;
	}
	  @Bean
	    public static BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}
