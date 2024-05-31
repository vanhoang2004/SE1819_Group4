package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public UserDetailsManager userDetailsManager (DataSource datasource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);
		jdbcUserDetailsManager.setUsersByUsernameQuery(
				"select username, password, enabled from users where username=?"
				);
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
				"select username, role from users where username=?"
				);
		return jdbcUserDetailsManager;
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer ->
			configurer 
					.requestMatchers("/student/**").hasRole("STUDENT")
					.requestMatchers("/teacher/**").hasRole("TEACHER")
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.requestMatchers("/manager/**").hasRole("MANAGER")
					.anyRequest().authenticated())
			.formLogin(form -> form
							.loginPage("/login")
							.loginProcessingUrl("/authenticate")
							.defaultSuccessUrl("/")
							.permitAll())
		  	.logout(logout -> logout.permitAll())
		  	.exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));
		return http.build();
	}
}
