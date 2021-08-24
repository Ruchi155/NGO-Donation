package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor 
@ToString
@Table
@Entity
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserProfile implements Serializable {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	private long id;
	private String address1;
	private String address2;
	private int CMA;
	private String phone;
	private String city;
	private String state;
	private int zipcode;
	private String country;
	private String urbanization;
	
	
	@OneToOne(
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			mappedBy = "userProfile"
			) 
	@JsonBackReference
	private Users user;
	
	public UserProfile(long id)
	{
		this.id=id;
	}
	
}
