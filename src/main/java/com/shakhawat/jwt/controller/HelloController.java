package com.shakhawat.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shakhawat.jwt.entity.AuthRequest;
import com.shakhawat.jwt.util.JwtUtil;

@RestController
public class HelloController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/")
	public String welcome() {
		return "Spring Security Project with JWT";
	}
	
	@PostMapping("/auth")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
		);
		} catch(Exception exception) {
			throw new Exception("Invalid Username/Password");
		}
		return jwtUtil.generateToken(authRequest.getUsername());
	}
}
