package com.example.demo.repo; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository; 
import com.example.demo.models.Donation;
 
@Repository
public interface DonationRepo extends JpaRepository<Donation, Long>{

}
