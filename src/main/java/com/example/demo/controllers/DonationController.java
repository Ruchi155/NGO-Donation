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
import com.example.demo.models.Donation;
import com.example.demo.services.DonationService;

@RestController() 
@RequestMapping("/donations")
public class DonationController {
	@Autowired
	DonationService donateService;
	
	@GetMapping("/")
	public ResponseEntity<List<Donation> >getAllDonation(){
		try {
			List<Donation> donations =  donateService.findAll();
			if(donations.isEmpty()) {
				return new ResponseEntity<List<Donation>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Donation>> (donations, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
			// TODO: handle exception
		} 
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<List<Donation> >getAllDonationByUserId(@PathVariable (value= "id") long userId){
		try {
			List<Donation> donations =  donateService.getAllDonationByUserId(userId);
			if(donations.isEmpty()) {
				return new ResponseEntity<List<Donation>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Donation>> (donations, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
			// TODO: handle exception
		} 
	}
	@PostMapping("/adddonation")
	public ResponseEntity<Donation> createDonation(@Valid @RequestBody Donation donation) {
		try {
			donateService.save(donation);
			return new ResponseEntity<Donation> (donation, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<Donation>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}
	@GetMapping("/finddonation/{id}")
	public ResponseEntity<Donation> getDonationById(@PathVariable(value = "id") Long donationId)  {
		Optional<Donation> donationData= donateService.findById(donationId);
		if(donationData.isPresent()) {
			return new ResponseEntity<Donation> (donationData.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		} 
	}
	@PutMapping("/updatedonation/{id}")
	public ResponseEntity<Donation> updateDonation(@PathVariable (value = "id") Long donationId,
									@Valid @RequestBody Donation donation)
	{
		Optional<Donation> donationData = donateService.findById(donationId);
		if(donationData.isPresent()) {
			Donation donationGet = donationData.get();
			donationGet = donation;
			return new ResponseEntity<>(donateService.save(donationGet), HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<Donation>(HttpStatus.NOT_FOUND);
		}
		
	}
	@DeleteMapping("/deletedonation/{id}")
	public ResponseEntity<HttpStatus> deleteDonation(@PathVariable (value = "id") Long donationId){ 
		try {
			donateService.deleteById(donationId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}  
	}
	@DeleteMapping("/deletealldonations")
	public ResponseEntity<HttpStatus> deleteAllDonation(){
		try {
			donateService.deleteAll();
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
							
}
