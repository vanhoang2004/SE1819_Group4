package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
	@Bean
	public UserDetailsManager userDetailsManager (DataSource datasource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);
		// define query to retrieve a user 
		jdbcUserDetailsManager.setUsersByUsernameQuery(
				"select username, password,enabled from users where username=?"
				);
		// define query to retrieve the authorities/roles 
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
				"select username, role from users where username=?"
				);
		return jdbcUserDetailsManager;
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer ->
			configurer 
					.requestMatchers("/students/**").hasRole("STUDENT")
					.requestMatchers("/teachers/**").hasRole("TEACHER")
					.requestMatchers("/admins/**").hasRole("ADMIN")
					.requestMatchers("/managers/**").hasRole("MANAGER")
					.anyRequest().authenticated()
				)
		.formLogin(form -> 
				form 
						.loginPage("/showMyLoginPage")
						.loginProcessingUrl("/authenticateTheUser")
						.defaultSuccessUrl("/students") 
						.permitAll()
				)
		  .logout(logout ->
          logout.permitAll()
      )
		  .exceptionHandling(configurer ->
		  		configurer.accessDeniedPage("/access-denied")
				  );

		http.csrf(csrf -> csrf.disable());
		return http.build();
	}
}
