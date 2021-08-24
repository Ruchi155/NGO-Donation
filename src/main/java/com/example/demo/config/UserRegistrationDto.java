 package com.example.demo.config;
import javax.validation.constraints.AssertTrue;

import javax.validation.constraints.Email;


import javax.validation.constraints.NotEmpty;

import com.example.constraint.FieldMatch;
import com.example.demo.models.UserProfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 

 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
    @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class UserRegistrationDto {
	
	@NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String username;
    

	@NotEmpty
    private String role;
    
    private UserProfile userprofile;

	@Email
    @NotEmpty
    private String email;

    @Email
    @NotEmpty
    private String confirmEmail;

    @AssertTrue
    private Boolean terms;

    
	
 
}