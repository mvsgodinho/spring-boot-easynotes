package com.experian.buname.easynotes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.experian.buname.easynotes.AppConstants;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
			.ignoringAntMatchers(AppConstants.API_BASE_URL + "**")
			.and()
			.authorizeRequests()
				// Home and Cors
				.antMatchers("/", "/csrf")
					.permitAll()
				// Swagger
				.antMatchers("/", "/swagger-ui.html**/**", "/v2/api-docs**", "/swagger-resources/**", "/webjars/**")
					.permitAll()
				// Notes API Create and Get by ID
				.antMatchers(HttpMethod.GET, AppConstants.Notes.PATH_V1 + "/*")
					.permitAll()
				.antMatchers(HttpMethod.POST, AppConstants.Notes.PATH_V1)
					.permitAll()
				.anyRequest()
					.authenticated()
					.and()
					.httpBasic();
	}
}
