package com.example.demo.repo;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Users;
 
@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

	Optional<Users> findUsersById(long id);
	
	@Query("SELECT u FROM Users u WHERE u.email = :email")
	Users findUserByEmail(@Param("email")String email);
}
