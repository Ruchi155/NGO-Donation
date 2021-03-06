
package com.example.demo.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
 
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

 
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.example.demo.handler.LoginSuccessHandler;
 import com.example.demo.config.JwtTokenProvider;
import com.example.demo.services.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserService uService;
	
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	 @Autowired
	    JwtTokenProvider jwtTokenProvider;
 
		
 
	@Override
	// provide access to some url, image, function
	protected void configure(HttpSecurity http) throws Exception {
 
		http.cors();
		/*
		 * Home page request to login with role User or Administrator if the user don't
		 * login then it redirect to /login ROLE_USER, ADMIN, PROVIDER is take from
		 * database
		 */
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/users/adduser**").permitAll();
		http.authorizeRequests().antMatchers("/homepage**").permitAll();
		/*
		 * If user login with ROLE_USER but try to access to admin page then send them a
		 * message denied
		 */
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.httpBasic()
		.disable().csrf().disable().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
        .antMatchers("/login").permitAll().antMatchers("/users/adduser").permitAll() 
        
        .and().csrf() .disable().exceptionHandling()
        
        .authenticationEntryPoint(unauthorizedEntryPoint()).and()
        .apply(new JwtConfigurer(jwtTokenProvider));
	//	http.httpBasic().authenticationEntryPoint(unauthorizedEntryPoint()).and().apply(new JwtConfigurer(jwtTokenProvider));
		http
		  	.csrf().disable()
		  	.authorizeRequests() 
				.and()
			.formLogin()
			
				.loginPage("/login")
				.permitAll()
				.successHandler(loginSuccessHandler)
				.and() 
			
			.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.permitAll();
	
	}
			/*
			 * Allow login with provider Role
			 */

//			http.authorizeRequests()
//
//					.and().formLogin().loginPage("/login")
//					// if successful then go to this page
//					.defaultSuccessUrl("/homepage/loginsucess")
//					// if not go error
//					.failureUrl("/login?error=true").usernameParameter("username")// parameter from FORM login ??? b?????c 3 c??
//																					// input name='username'
//					.passwordParameter("password")// parameter from FORM login ??? b?????c 3 c?? input name='password'
//					.permitAll();
//
//			http.authorizeRequests().and().logout().invalidateHttpSession(true).clearAuthentication(true)
//					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/?logout").permitAll();
           
		//Only admin can do that: route- routeGuard -> give admin role to do that
		//login SUCCESSLY -> USERS database -> return role -> verify routeGuard
		//ADDUSER = REGISTER
		//return user First Name
		//type wrong password
		
 
	 
	
	@Bean("authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized");
    }
	 
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authencationProvider()
	{
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(uService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	{
		auth.authenticationProvider(authencationProvider());
	}
 
 
 
	 
}
