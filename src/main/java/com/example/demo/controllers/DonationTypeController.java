package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import com.example.demo.models.DonationType; 
import com.example.demo.services.DonationTypeService;

@RestController
@RequestMapping("/donationTypes")
public class DonationTypeController {
	@Autowired
	DonationTypeService donationTypeService;
	
	@GetMapping("/")
	public ResponseEntity<List<DonationType> >getAllDonation(){
		try {
			List<DonationType> donations =  donationTypeService.findAll();
			if(donations.isEmpty()) {
				return new ResponseEntity<List<DonationType>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<DonationType>> (donations, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
		} 
	}
}
