package com.shopsense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.shopsense.dao.AdminDA;
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

	public AuthResponse login(AuthRequest a) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(a.getEmail(), a.getPassword()));
		var user = adminDA.findByEmail(a.getEmail());
		var token = jwtService.generateToken(user);
		return AuthResponse.builder().token(token).user(user).build();
	}
}
