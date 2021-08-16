package com.example.demo; 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest; 
import com.example.demo.models.Users;
import com.minhle.ngo_disater.repo.DonationRepo;
import com.minhle.ngo_disater.repo.UserRepo;

@SpringBootTest
class NgoDonationApplicationTests {

	@Test
	void contextLoads() {
}
//	@Autowired
//	DonationRepo donatioRepo;
	@Autowired 
	UserRepo userRepo;
//	@Test
//	void testCreateDonation() {
//		Users u1 = userRepo.findById(1L).get();
//		DonationType t1 = DonationType.builder().
//										name("Food for student").build();
//		Donation donation = Donation.builder()
//									.amount(12.0)
//									.date(LocalDate.now())
//									.donationType(t1)
//									.user(u1)
//									.build();
//		donatioRepo.save(donation);
//	}
	@Test
	void testCreateUser() {
		Users user =  Users.builder()
				.email("minhbac3@gmail.com")
				.firstName("minh")
				.lastName("ke")
				.password("1234").build();
		userRepo.save(user);
	}


}
