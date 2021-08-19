package com.example.demo.controllers;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.config.UserRegistrationDto;
import com.example.demo.models.UserProfile;
import com.example.demo.models.Users;
import com.example.demo.services.UserProfileService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private UserProfileService userprofileserv;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result){

        Users existing = userService.findUserByEmail(userDto.getEmail());
        
        
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration";
        }
        
        
        
        userService.save(userDto);
        
       
        return "redirect:/registration?success";
    }
    

}
