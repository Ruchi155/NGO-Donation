package com.example.demo; 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.Donation;
import com.example.demo.models.DonationType;
import com.example.demo.models.Role;
import com.example.demo.models.UserProfile;
import com.example.demo.models.Users;
import com.example.demo.repo.DonationRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.services.DonationService;
import com.example.demo.services.DonationTypeService; 

@SpringBootTest
class NgoDonationApplicationTests {

	@Test
	void contextLoads() {
}
	@Autowired
	DonationRepo donatioRepo;
	@Autowired 
	UserRepo userRepo;


	/*
	 * @Test void testCreateDonation() { Users u1 = userRepo.findById(1L).get();
	 * DonationType t1 = DonationType.builder(). name("Food for student").build();
	 * Donation donation = Donation.builder() .donationType(t1) .build();
	 * donatioRepo.save(donation); }
	 */
	 
//	@Test
//	void testCreateUser() {
//		Users user =  Users.builder()
//				.email("minhbac3@gmail.com")
//				.firstName("minh") 
//				.password("1234").build();
	@Autowired
	DonationTypeService donationTypeService;
	@Autowired
	DonationService donationSerivce ;
	  @Test void testCreateDonation() { 
		  Users u1 = userRepo.findById(48L).get();
		  DonationType t1 = donationTypeService.findById(1L).get();
		  Donation donation = Donation.builder() .donationType(t1).amount(100.0).user(u1) .build();
		  donatioRepo.save(donation);
		  }

//	 
//	@Test void testCreatUserProfile() {
//		  Users u1 = userRepo.findById(48L).get();
//		UserProfile userProfile = UserProfile.builder().address1("3353 Trebol ln, CA").city("San Jose").build();
//		u1.setUserProfile(userProfile);
//		  userRepo.save(u1);
//	}
//	@Test
//	void testCreateUser() {
//		Role r1 = Role.builder().name("FEEDER").build();
//		
//		Collection<Role> inputRole = new ArrayList<Role>();
//		inputRole.add(Role.builder().name("ADMIN").build());
//		Users user =  Users.builder()
//				.email("wadam@gmail.com")
//				.firstName("minh") 
//				.password("1234")
//				.roles(inputRole)
//				.build();
//		UserProfile userProfile = UserProfile.builder().address1("3353 Trebol ln, CA").city("San Jose").build();
//		user.setUserProfile(userProfile);
//		userRepo.save(user);
//	}
//	
//	 @Test void testCreateDonation() {
//		 Users u1 = userRepo.findById(1L).get();
//		 DonationType t1 = DonationType.builder(). name("Food for student").build();
//		 Donation donation = Donation.builder() .date(LocalDate.now()) .amount(12.0)
//				 .donationType(t1) .user(u1) .build(); 
//		 donatioRepo.save(donation); }
	 
	/*
	 * @Test void testCreateUser() { Users user = Users.builder()
	 * .email("minhbac3@gmail.com") .firstName("minh") .password("1234").build();
	 * 
	 * userRepo.save(user); }
	 */
}
