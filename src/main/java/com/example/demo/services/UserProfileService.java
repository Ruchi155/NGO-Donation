package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.UserProfile;
import com.example.demo.repo.UserProfileRepo;

@Service
public class UserProfileService {
@Autowired
UserProfileRepo userProfileRepo;
public List<UserProfile> findAll(){
	return userProfileRepo.findAll();
}
public Optional<UserProfile >findById(long id) {
	return userProfileRepo.findById(id);
}
 
public void deleteById(long id) {
	userProfileRepo.deleteById(id);
}
public UserProfile save(UserProfile UserProfile ) {
	return userProfileRepo.save(UserProfile);
}
public void deleteAll() {
	userProfileRepo.deleteAll();
}
}
