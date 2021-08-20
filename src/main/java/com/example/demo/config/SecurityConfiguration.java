package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.services.UserService;

	
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
	{
		@Autowired
		UserService userService;

		// providing access to some type of urls for reg ,login, css, jss,html images etc
		@Override
		// provide access to some url, image, function
		protected void configure(HttpSecurity http) throws Exception {
			// TODO Auto-generated method stub
			http.csrf().disable(); // CSRF ( Cross Site Request Forgery)
//			http.cors();
			/*
			 * Home page request to login with role User or Administrator if the user don't
			 * login then it redirect to /login ROLE_USER, ADMIN, PROVIDER is take from
			 * database
			 */
			http.authorizeRequests().antMatchers("/").permitAll();
			http.authorizeRequests().antMatchers("/registration**").permitAll();
			http.authorizeRequests().antMatchers("/homepage**").permitAll();
			/*
			 * If user login with ROLE_USER but try to access to admin page then send them a
			 * message denied
			 */
			http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		 
			// http.antMatcher("/provider*").authorizeRequests().anyRequest().hasRole("PROVIDER");

			/*
			 * Allow login with provider Role
			 */

			http.authorizeRequests()

					.and().formLogin().loginPage("/login")
					// if successful then go to this page
					.defaultSuccessUrl("/homepage/loginsucess")
					// if not go error
					.failureUrl("/login?error=true").usernameParameter("username")// parameter from FORM login ở bước 3 có
																					// input name='username'
					.passwordParameter("password")// parameter from FORM login ở bước 3 có input name='password'
					.permitAll();

			http.authorizeRequests().and().logout().invalidateHttpSession(true).clearAuthentication(true)
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/?logout").permitAll();

		}
//	@Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
//        // setAllowCredentials(true) is important, otherwise:
//        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
//        configuration.setAllowCredentials(true);
//        // setAllowedHeaders is important! Without it, OPTIONS preflight request
//        // will fail with 403 Invalid CORS request
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
		
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authencationProvider()
	{
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService((UserDetailsService) userService);
		// you can add more services here...
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	{
		auth.authenticationProvider(authencationProvider());
	}
}

	

