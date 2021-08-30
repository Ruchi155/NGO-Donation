package com.example.demo.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtTokenProvider;
import com.example.demo.models.AuthBody;
import com.example.demo.models.Users;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class LoginRegistrationController {
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	@Autowired
	UserService userserv;
	   
    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthBody data) {
        try {
            String userEmail = data.getUserEmail();
            String password = data.getUserPassword();
            System.out.println(userEmail + " and " +password);
            
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, password));
            String token = jwtTokenProvider.createToken(userEmail, this.userserv.findUserByEmail(userEmail).getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("userEmail", userEmail);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }
    @PostMapping("/register")
	public ResponseEntity  addUser(@RequestBody Users user)
	{ 
		
		Users userExists = userserv.findUserByEmail(user.getEmail());
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + user.getEmail() + " already exists");
        }
		userserv.registration(user);
		Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        return ok(model);
	}
}
