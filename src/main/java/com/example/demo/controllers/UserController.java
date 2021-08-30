package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtTokenProvider;
import com.example.demo.models.AuthBody;
import com.example.demo.models.Donation;
import com.example.demo.models.UserProfile;
import com.example.demo.models.Users;
import com.example.demo.repo.UserRepo;
import com.example.demo.services.UserService; 
import org.springframework.security.core.AuthenticationException;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.security.authentication.BadCredentialsException;
@RestController() 
@RequestMapping("/users")
@CrossOrigin
public class UserController
{

	@Autowired
	UserService userserv;
  
  @Autowired
  UserRepo userRepo;
    
	/* User Controller */
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/")
	public ResponseEntity<List<Users>> getAllUsers()
	{
		List<Users> users = userserv.findAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	} 
	@RequestMapping(value = "/finduserEmail/{email}", method = RequestMethod.GET)
	public ResponseEntity<Users> getUserByEmail(@PathVariable("email") String email)
	{
		 
		
		try {
			 Users users = userRepo.findUserByEmail(email);
			 
			return new ResponseEntity<Users> (users, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
			// TODO: handle exception
		} 
	}
	
	@GetMapping("/finduser/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable("id") long id)
	{
		Users user = userserv.findUserById(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
 
	
	@PutMapping("/updateuser/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable("id") long id, @RequestBody Users user)
	{
		
		Users temp = userserv.getUserById(id); 
		 temp =user;
		 Users updateuser = userserv.updateUser(temp);
		return new ResponseEntity<>(updateuser,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id)
	{
		userserv.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	 
}
