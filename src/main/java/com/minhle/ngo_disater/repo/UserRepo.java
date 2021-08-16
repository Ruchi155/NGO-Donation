package com.minhle.ngo_disater.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Users;
 
@Repository
public interface UserRepo extends CrudRepository<Users, Long> {

}
