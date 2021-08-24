package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Donation;
import com.example.demo.models.DonationType;
import com.example.demo.repo.DonationRepo;
import com.example.demo.repo.DonationTypeRepo;

@Service
public class DonationTypeService {
	@Autowired
	private DonationTypeRepo donationTypeRepo;
	public List<DonationType> findAll(){
		return donationTypeRepo.findAll();
	}
	public Optional<DonationType >findById(long id) {
		return donationTypeRepo.findById(id);
	}
	 
	public void deleteById(long id) {
		donationTypeRepo.deleteById(id);
	}
	public DonationType save(DonationType donation ) {
		return donationTypeRepo.save(donation);
	}
	public void deleteAll() {
		donationTypeRepo.deleteAll();
	}
}
