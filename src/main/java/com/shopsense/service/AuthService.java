package com.shopsense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.shopsense.dao.AdminDA;
import com.shopsense.dao.CustomerDA;
import com.shopsense.dao.SellerDA;
import com.shopsense.dto.AuthRequest;
import com.shopsense.dto.AuthResponse;
import com.shopsense.security.JwtService;

@Service
public class AuthService {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtService jwtService;

	@Autowired
	AdminDA adminDA;
	
	@Autowired
	CustomerDA customerDA;
	
	@Autowired
	SellerDA sellerDA;

	public AuthResponse login(AuthRequest a) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(a.getEmail(), a.getPassword()));
		var user = adminDA.findByEmail(a.getEmail());
		var token = jwtService.generateToken(user);
		user.setPassword(null);
		return AuthResponse.builder().status("success").token(token).user(user).build();
	}
	
	public AuthResponse customerLogin(AuthRequest a) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(a.getEmail(), a.getPassword()));
		var user = customerDA.findByEmail(a.getEmail());
		var token = jwtService.generateToken(user);
		user.setPassword(null);
		return AuthResponse.builder().status("success").token(token).user(user).build();
	}
	
	public AuthResponse sellerLogin(AuthRequest a) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(a.getEmail(), a.getPassword()));
		var user = sellerDA.findByEmail(a.getEmail());
		var token = jwtService.generateToken(user);
		user.setPassword(null);
		return AuthResponse.builder().status("success").token(token).user(user).build();
	}
}
