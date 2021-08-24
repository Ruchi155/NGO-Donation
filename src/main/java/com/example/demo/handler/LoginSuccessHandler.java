package com.example.demo.handler;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
 

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
        Object userDetails = authentication.getPrincipal();
        
        if(userDetails.toString().contains("Admin")) {
        	
        }
         System.out.println(userDetails.toString());
        String redirectURL = request.getContextPath();
         
        if (userDetails.toString().contains("Admin")) {
            redirectURL = "/admin";
        } else if (userDetails.toString().contains("User")) {
            redirectURL = "/user";
        }
        
        response.sendRedirect(redirectURL);
         
    }
 
}