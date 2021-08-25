package com.example.demo;
 
import java.util.Arrays;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter; 

@SpringBootApplication
public class NgoDonationApplication extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		SpringApplication.run(NgoDonationApplication.class, args);
	} 
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return builder.sources(NgoDonationApplication.class); 
	} 
	@Bean
	public CorsFilter corsFilter()
	{
		CorsConfiguration corsConfiguration= new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin",
														  "Access-Control-Allow-Origin", 
														  "Content-Type",
														  "Accept", 
														  "Authorization",  
														  "X-Requested-With",
														  "Access-Control-Request-Method", 
														  "Access-Control-Request-Headers",
														  "Access-Control-Allow-Credential"));
		//This code is for working when upload object with json style
		corsConfiguration.setAllowedHeaders(Arrays.asList("Content-Type","*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
	    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
	    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
	    return new CorsFilter(urlBasedCorsConfigurationSource); 
	}  
}

