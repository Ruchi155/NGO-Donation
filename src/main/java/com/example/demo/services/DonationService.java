package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Donation;
import com.example.demo.repo.DonationRepo;
@Service
public class DonationService {
	@Autowired
	private DonationRepo donationRepo;
	public List<Donation> findAll(){
		return donationRepo.findAll();
	}
	public Optional<Donation >findById(long id) {
		return donationRepo.findById(id);
	}
	 
	public void deleteById(long id) {
		donationRepo.deleteById(id);
	}
	public Donation save(Donation donation ) {
		return donationRepo.save(donation);
	}
	public void deleteAll() {
		donationRepo.deleteAll();
	}
}
