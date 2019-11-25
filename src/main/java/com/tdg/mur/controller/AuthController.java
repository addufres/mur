package com.tdg.mur.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdg.mur.dto.AuthenticationResponse;
import com.tdg.mur.dto.LoginRequest;
import com.tdg.mur.dto.RegisterRequest;
import com.tdg.mur.service.AuthService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
	
	 private final AuthService authService;

	    @SuppressWarnings("rawtypes")
		@PostMapping("/signup")
	    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
	        authService.signup(registerRequest);
	        log.info("New user registered: " + registerRequest.toString());
	        return new ResponseEntity<>(OK);
	    }

	    @PostMapping("/login")
	    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
	    	log.info("Login request by: " + loginRequest.toString());
	        return authService.login(loginRequest);
	    }

	    @GetMapping("accountVerification/{token}")
	    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
	        authService.verifyAccount(token);
	        log.info("New user verified successfully.");
	        return new ResponseEntity<>("Account Activated Successfully", OK);
	    }
}
