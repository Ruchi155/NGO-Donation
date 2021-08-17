package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.models.Users;
import com.example.demo.repo.UserRepo;

@Service
public class UserService 
{
	@Autowired
	UserRepo userrepo;
	
	
	/*
	 * public void updateUser(Long id, Users u) { Users temp = userrepo.getById(id);
	 * temp.setFirstName(u.getFirstName()); temp.setLastName(u.getLastName());
	 * temp.setEmail(u.getEmail()); userrepo.save(temp); }
	 */

	public Users getUserById(long id) {
		return userrepo.findById(id).get();
	}

	public Users addUser(Users u) {
		return userrepo.save(u);
	}

	public void deleteUser(long id) {
		userrepo.deleteById(id);
	}

	public Users updateUser(Users user) {
		return userrepo.save(user);
	}
	
	public List<Users> getAllUsers() {
		return userrepo.findAll();
	}

	
	public void SaveUser(Users u) {
		// TODO Auto-generated method stub
		userrepo.save(u);
		
	}

	public Users get(long i) {
		// TODO Auto-generated method stub
		return userrepo.findById(i).get();
	}

	public Users findUserById(long id)
	{
		return userrepo.findUsersById(id)
				.orElseThrow(()-> new UserNotFoundException("User by id" + id + "was not found"));
				
	}

	public List<Users> findAllUsers() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
	}

}
