package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.DonationType;
@Repository
public interface DonationTypeRepo extends JpaRepository<DonationType, Long>{

}
