package com.shopsense.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shopsense.dao.AdminDA;

@Configuration
public class ApplicationConfig {
	
	@Autowired
	AdminDA adminDa;
	
//	@Bean
//	AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService());
//		return authenticationProvider;
//	}
	
	@Bean
	AuthProvider authProvider() {
		AuthProvider authProvider = new AuthProvider(userDetailsService());
		return authProvider;
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return adminDa.findByEmail(username);
			}
		};
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
