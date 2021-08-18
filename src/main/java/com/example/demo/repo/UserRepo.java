package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Users;
 
@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

	Optional<Users> findUsersById(long id);
	
	@Query("from Users where email =: email")
	Users findByEmail(@Param ("email") String email);
}
