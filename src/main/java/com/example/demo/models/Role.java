package com.example.demo.models;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity (name = "Role")
@Data
@ToString
@Table(name = "Role")
public class Role
{		
 	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;  
}



