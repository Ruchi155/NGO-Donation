package com.example.demo.models;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table; 
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data 
@ToString
@Table(name = "Donation")
@Entity(name =  "Donation")
@NoArgsConstructor  
public class Donation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id")
	private Long id;
	@Column(name =  "name")
	private String Name;
	@Column(name =  "date")
	private LocalDate date;
	@Column(name =  "amount")
	private double amount;
	@Column(name =  "donation_type")
	private String DonationType;
	public Donation(String name, LocalDate date, double amount, String donationType) {
		super();
		Name = name;
		this.date = date;
		this.amount = amount;
		DonationType = donationType;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "user_id")
	private Users user;
}
