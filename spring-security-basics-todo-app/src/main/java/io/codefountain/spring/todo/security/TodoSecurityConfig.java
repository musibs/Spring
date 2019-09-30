package io.codefountain.spring.todo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class TodoSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("cook").password("{noop}iamcook").authorities("USER")
			.and()
			.withUser("chef").password("{noop}iamchef").authorities("USER","ADMIN");
	}
	
	/**
	 * Form Based login
	 */
	
	
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception { http
	 * .authorizeRequests() .antMatchers("/todos*", "/error").hasAuthority("USER")
	 * .antMatchers("/todoDelete/*").hasAuthority("ADMIN")
	 * .antMatchers(HttpMethod.POST, "/todo*").hasAuthority("ADMIN") .and()
	 * .formLogin(); }
	 */
	 
	
	/**
	 * Basic Authentication
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/todos").hasAuthority("USER")
			.antMatchers("/todoDelete/*").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.POST, "/todo*").hasAuthority("ADMIN")
			.and()
			//.httpBasic();
			.formLogin()
		/*	.and()
			.anonymous()
			.principal("guest")
			.authorities("ROLE_GUEST");*/
			.and()
			.rememberMe();
	}
}
