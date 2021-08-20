package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/profiles")
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 5000)
public class UserProfileController {
	@Autowired
	UserProfileService profileService;
	
	@GetMapping("/")
	public ResponseEntity<List<UserProfile> >getAllProfile(){
		try {
			List<UserProfile> userProfiles =  profileService.findAll();
			if(userProfiles.isEmpty()) {
				return new ResponseEntity<List<UserProfile>>(HttpStatus.NO_CONTENT);
			}
			System.out.println(userProfiles.size());
			return new ResponseEntity<List<UserProfile>> (userProfiles, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
			// TODO: handle exception
		} 
	}
	@PostMapping("/addprofile")
	public ResponseEntity<UserProfile> createDonation(@Valid @RequestBody UserProfile donation) {
		try {
			profileService.save(donation);
			return new ResponseEntity<UserProfile> (donation, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<UserProfile>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}
	@GetMapping("/findprofile/{id}")
	public ResponseEntity<UserProfile> getDonationById(@PathVariable(value = "id") Long donationId)  {
		Optional<UserProfile> userProfileData= profileService.findById(donationId);
		if(userProfileData.isPresent()) {
			return new ResponseEntity<UserProfile> (userProfileData.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		} 
	}
	@PutMapping("/updateprofile/{id}")
	public ResponseEntity<UserProfile> updateDonation(@PathVariable (value = "id") Long donationId,
									@Valid @RequestBody UserProfile profile)
	{
		Optional<UserProfile> userProfileData = profileService.findById(donationId);
		if(userProfileData.isPresent()) {
			UserProfile profileGet = userProfileData.get();
			profileGet = profile;
			System.out.println(profileGet);
			return new ResponseEntity<>(profileService.save(profileGet), HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<UserProfile>(HttpStatus.NOT_FOUND);
		}
		
	}
	@DeleteMapping("/deleteprofile/{id}")
	public ResponseEntity<HttpStatus> deleteDonation(@PathVariable (value = "id") Long donationId){ 
		try {
			profileService.deleteById(donationId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}  
	}
	@DeleteMapping("/deleteallprofiles")
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
