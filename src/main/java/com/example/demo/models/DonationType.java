package com.example.demo.models; 
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Builder;
import lombok.Data;

@Table(
		name = "DonationType", 
		uniqueConstraints = @UniqueConstraint(columnNames = {"name"})
	  ) 
@Entity(name = "DonationType")
@Data
@Builder
public class DonationType implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
//	@OneToOne(
//			fetch = FetchType.LAZY,
//			cascade = CascadeType.ALL,
//			mappedBy = "donationType"
//			) 
//	private Donation donation;
}
