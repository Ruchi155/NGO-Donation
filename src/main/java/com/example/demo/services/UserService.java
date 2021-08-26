package com.example.demo.services; 

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
 
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.models.Role;
import com.example.demo.models.UserProfile;
import com.example.demo.models.Users;
import com.example.demo.repo.UserRepo;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService  
{
	@Autowired
	UserRepo userrepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	public Collection<Role> getRole(Users user) {
		return user.getRoles();
	}
	public Users registration(Users users)
	{
		Users user = new  Users(); 
		user.setFirstName(users.getFirstName());
		user.setLastName(users.getLastName());
		user.setEmail(users.getEmail());
		user.setPassword(passwordEncoder.encode(users.getPassword()));
	    user.setRoles(  users.getRoles()); 
		user.setUserProfile(new UserProfile());
		return userrepo.save(user); 
	 } 
	public Users findUserByEmail(String email) {
		return userrepo.findUserByEmail(email);
	}
	

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userrepo.findUserByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        } 
       return new  User(user.getEmail(),
        user.getPassword(),
        mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }  
	public Users loadUserByEmail(String email) {
		return userrepo.findUserByEmail(email);
	}
	public Users getUserById(long id) {
		return userrepo.findById(id).get();
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
	
	public void saveUser(Users u) {
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
