package com.example.demo.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NGOController 
{
//	@PostMapping("/logout")
//	public String logoutDo(HttpServletRequest request,HttpServletResponse response){
//	HttpSession session= request.getSession(false);
//	    SecurityContextHolder.clearContext();
//	         session= request.getSession(false);
//	        if(session != null) {
//	            session.invalidate();
//	        }
//	        for(Cookie cookie : request.getCookies()) {
//	            cookie.setMaxAge(0);
//	        }
//
//	    return "login";
//	}
	@RequestMapping()
	public String main() {
		return "login";
		
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String root()
	
	{
		return "login";
		
	}
	
	
	

}
