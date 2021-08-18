package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Users;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/ngodonation")
public class UserController
{

	@Autowired
	UserService userserv;
	
	/* User Controller */
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/all")
	public ResponseEntity<List<Users>> getAllUsers()
	{
		List<Users> users = userserv.findAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable("id") long id)
	{
		Users user = userserv.findUserById(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Users> addUser(@RequestBody Users user)
	{
		Users newuser = userserv.addUser(user);
		return new ResponseEntity<>(newuser,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable("id") long id, @RequestBody Users user)
	{
		
		Users temp = userserv.getUserById(id);
		 temp.setFirstName(user.getFirstName()); 
		 temp.setLastName(user.getLastName());
		 temp.setEmail(user.getEmail()); 
		 temp.setPassword(user.getPassword());
		 temp.setRoles(user.getRoles());
		 Users updateuser = userserv.updateUser(temp);
		return new ResponseEntity<>(updateuser,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id)
	{
		userserv.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	//Using thymeleaf
	/*
	 * @RequestMapping("list_users") public String listusers(Model model) {
	 * List<Users> list = userserv.getAllUsers(); model.addAttribute("users", list);
	 * return "user"; }
	 * 
	 * @RequestMapping("/newuser") public String addnewUser(Model model) { Users m =
	 * new Users(); model.addAttribute("user", m); return "new_user"; }
	 * 
	 * @RequestMapping(value= "/saveuser", method=RequestMethod.POST) public String
	 * saveUser(@ModelAttribute("user") Users u) { userserv.SaveUser(u); return
	 * "redirect:/list_users"; //return "redirect:/admin/list_users"; }
	 * 
	 * @RequestMapping(value= "/saveuser/{id}", method=RequestMethod.POST) public
	 * String saveUser(@ModelAttribute("user") Users u ,@PathVariable(name="id")
	 * long id) { userserv.deleteUserById(id); userserv.SaveUser(u); return
	 * "redirect:/list_users"; //return "redirect:/admin/list_users"; }
	 * 
	 * @RequestMapping("/edituser/{id}") public ModelAndView
	 * showEditUserPage(@PathVariable(name="id") long id) { ModelAndView mav= new
	 * ModelAndView("edit_user"); Users m= userserv.get(id);
	 * mav.addObject("user",m); return mav; }
	 * 
	 * @RequestMapping("/deleteuser/{id}") public String
	 * deleteUser(@PathVariable(name="id") long id) { userserv.deleteUserById(id);
	 * return "redirect:/list_users"; }
	 */
}
