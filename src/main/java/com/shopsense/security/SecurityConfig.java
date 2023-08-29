package com.shopsense.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.shopsense.model.Role;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	JwtAuthFilter jwtAuthFilter;
	
	@Autowired
	AuthProvider authProvider;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));
        
		return http
				.csrf(csrf -> csrf.disable())
				.cors(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(
								"/admin/login",
								"/seller/login",
								"/seller/signup",
								"/customer/login",
								"/customer/signup"
						).permitAll()
						.requestMatchers("/admin/**").hasAuthority(Role.ADMIN.name())
						.requestMatchers("/seller/**").hasAuthority(Role.SELLER.name())
						.requestMatchers("/customer/**").hasAuthority(Role.CUSTOMER.name())
						.requestMatchers(
								"/coupon/check",
								"/collectionpoint/search",
								"/upload",
								"/uploads/**",
								"/reports/**",
								"/**"
						).permitAll()
						.anyRequest().authenticated())
				.authenticationProvider(authProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
}
