package com.shopsense.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shopsense.dao.AdminDA;
import com.shopsense.dao.CustomerDA;
import com.shopsense.dao.SellerDA;

@Configuration
public class ApplicationConfig {
	
	@Autowired
	AdminDA adminDA;
	
	@Autowired
	CustomerDA customerDA;
	
	@Autowired
	SellerDA sellerDA;
	
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
				UserDetails u = null;
				u = adminDA.findByEmail(username);
				if(u == null) {
					u = customerDA.findByEmail(username);
				}
				if(u == null) {
					u = sellerDA.findByEmail(username);
				}
				return u;
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
