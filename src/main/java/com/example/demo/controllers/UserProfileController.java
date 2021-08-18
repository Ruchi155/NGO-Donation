package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.UserProfile;
import com.example.demo.services.UserProfileService;
@RequestMapping("/ngodonation")
@RestController
public class UserProfileController {
	@Autowired
	UserProfileService profileService;
	
	@GetMapping("/profiles")
	public ResponseEntity<List<UserProfile> >getAllProfile(){
		try {
			List<UserProfile> donations =  profileService.findAll();
			if(donations.isEmpty()) {
				return new ResponseEntity<List<UserProfile>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<UserProfile>> (donations, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
			// TODO: handle exception
		} 
	}
	@PostMapping("/profiles")
	public ResponseEntity<UserProfile> createDonation(@Valid @RequestBody UserProfile donation) {
		try {
			profileService.save(donation);
			return new ResponseEntity<UserProfile> (donation, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<UserProfile>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}
	@GetMapping("/profiles/{id}")
	public ResponseEntity<UserProfile> getDonationById(@PathVariable(value = "id") Long donationId)  {
		Optional<UserProfile> userProfileData= profileService.findById(donationId);
		if(userProfileData.isPresent()) {
			return new ResponseEntity<UserProfile> (userProfileData.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		} 
	}
	@PutMapping("/profiles/{id}")
	public ResponseEntity<UserProfile> updateDonation(@PathVariable (value = "id") Long donationId,
									@Valid @RequestBody UserProfile profile)
	{
		Optional<UserProfile> userProfileData = profileService.findById(donationId);
		if(userProfileData.isPresent()) {
			UserProfile profileGet = userProfileData.get();
			profileGet = profile;
			return new ResponseEntity<>(profileService.save(profileGet), HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<UserProfile>(HttpStatus.NOT_FOUND);
		}
		
	}
	@DeleteMapping("/profiles/{id}")
	public ResponseEntity<HttpStatus> deleteDonation(@PathVariable (value = "id") Long donationId){ 
		try {
			profileService.deleteById(donationId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}  
	}
	@DeleteMapping("/profiles")
	public ResponseEntity<HttpStatus> deleteAllDonation(){
		try {
			profileService.deleteAll();
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}