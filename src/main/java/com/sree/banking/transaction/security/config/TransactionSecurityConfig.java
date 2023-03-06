package com.sree.banking.transaction.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Sreenivasulu.Avula
 *
 */

@Configuration
public class TransactionSecurityConfig {
	
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf().disable().authorizeHttpRequests()
	        .requestMatchers("/*").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        .httpBasic()
	        .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    return http.build();
	  }
	
	
	 @Bean
	  public InMemoryUserDetailsManager userDetailsService() {
	    UserDetails user = User
	        .withUsername("sree")
	        .password(passwordEncoder().encode("eers"))
	        .roles("USER_ROLE")
	        .build();
	    return new InMemoryUserDetailsManager(user);
	  }
	
	@Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(8);
	  }

}
