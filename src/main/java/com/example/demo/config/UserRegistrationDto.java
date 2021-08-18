 package com.example.demo.config;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.example.demo.constrain.FieldMatch;

import lombok.AllArgsConstructor;
import lombok.Data; 
import lombok.NoArgsConstructor; 

 
@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
    @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class UserRegistrationDto { 
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
 
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String confirmPassword;
	
	@Email
	@NotEmpty
	private String email;
	
	@Email
	@NotEmpty
	private String confirmEmail; 
 
	
 
}