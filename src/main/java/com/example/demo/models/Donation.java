package com.example.demo.models;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data 
@ToString
@Table(name = "Donation")
@Entity(name =  "Donation")
@NoArgsConstructor  
@AllArgsConstructor
@Builder
public class Donation implements Serializable{
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
	
	@OneToOne(fetch = FetchType.LAZY, 
			 cascade = CascadeType.ALL )
	@JoinColumn(name = "type_id", nullable =  false)
	private DonationType donationType;  
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "user_id")
	private Users user;
}
