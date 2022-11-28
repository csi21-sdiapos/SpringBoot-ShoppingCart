package com.shoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// @ComponentScan(basePackages = { "com.shoppingcart.config" })
/*
 * Spring Security without the WebSecurityConfigurerAdapter
 * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
 */
public class SecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;
	    
	/*
	 * Class BCryptPasswordEncoder
	 * https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html
	 * 
	*/
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {   
		return new BCryptPasswordEncoder();
	}
		
	/*
	 * Interface AuthenticationManager
	 * https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/authentication/AuthenticationManager.html
	 * 
	 * Class AuthenticationManagerBuilder
	 * https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/config/annotation/authentication/builders/AuthenticationManagerBuilder.html
	 * 
	 * Class HttpSecurity
	 * https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/builders/HttpSecurity.html
	*/
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return
			http
	        	.getSharedObject(AuthenticationManagerBuilder.class)
	        		.userDetailsService(userDetailsService)
	        		.passwordEncoder(passwordEncoder())
	        		.and()
	        		
	        	.build();
	}

	/*
	 * Interface SecurityFilterChain
	 * https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/SecurityFilterChain.html
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests()
				.requestMatchers("/", "/webjars/**", "/css/**", "/h2-console/**", "/public/**", "/auth/**", "/files/**")
				.permitAll()
				.anyRequest().authenticated()
				.and()
				
			.formLogin()
				.loginPage("/auth/login")
				.defaultSuccessUrl("/public/index", true)
				.loginProcessingUrl("/auth/login-post")
				.permitAll()
				.and()
				
			.logout()
				.logoutUrl("/auth/logout")
				.logoutSuccessUrl("/public/index");
	       
		http.csrf().disable();				   		// to access to h2 db
		http.headers().frameOptions().disable();	// to access to h2 db
			
		return http.build();
	}
	
	/*********************** versión anterior (extends WebSecurityConfigurerAdapter) *********************/
/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/webjars/**", "/css/**", "/h2-console/**", "/public/**", "/auth/**", "/files/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/auth/login")
				.defaultSuccessUrl("/public/index", true)
				.loginProcessingUrl("/auth/login-post")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/auth/logout") 
				.logoutSuccessUrl("/public/index");
		
		http.csrf().disable();
        http.headers().frameOptions().disable();
		
	}
*/
}
