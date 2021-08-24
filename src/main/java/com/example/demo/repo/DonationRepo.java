package com.example.demo.repo; 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; 
import com.example.demo.models.Donation;
import com.example.demo.models.Users;
 
@Repository
public interface DonationRepo extends JpaRepository<Donation, Long>{
	@Query("SELECT d FROM Donation d WHERE d.user.id = :id")
	List<Donation>getDonationByUserId(@Param("id")long userId);
	
 
}
