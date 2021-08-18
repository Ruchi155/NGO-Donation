package com.example.demo.models; 

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity (name = "Role")
@Data
@ToString
@Table(name = "Role")
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable
{		
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name; 
    public Role(String name) {
        this.name = name;
    }

}


