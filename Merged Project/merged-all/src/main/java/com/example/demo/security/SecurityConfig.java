package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Autowired
	private DisabledAccountFailureHandler disabledAccountFailureHandler;

	@Autowired
	private DataSource dataSource;

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
	public AuthenticationProvider authenticationProvider() {
		UserDetailsManager userDetailsManager = userDetailsManager(dataSource);
		return new AuthenticationProvider() {
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				String username = authentication.getName();
				String password = authentication.getCredentials().toString();

				UserDetails user = userDetailsManager.loadUserByUsername(username);
				String userDetailsPassword = user.getPassword();
				if(userDetailsPassword.startsWith("{noop}")) userDetailsPassword = userDetailsPassword.substring(6);

				if (!passwordEncoder().matches(password, userDetailsPassword)) {
					throw new BadCredentialsException("Bad credentials");
				}

				if (!user.isEnabled()) {
					throw new DisabledException("User is disabled");
				}

				return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
			}

			@Override
			public boolean supports(Class<?> authentication) {
				return authentication.equals(UsernamePasswordAuthenticationToken.class);
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer ->
			configurer
					.requestMatchers("/student/**").hasRole("STUDENT")
					.requestMatchers("/teacher/**").hasRole("TEACHER")
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.requestMatchers("/manager/**").hasRole("MANAGER")
					.requestMatchers("/login").permitAll()
					.requestMatchers("/forgotpassword").permitAll()
					.requestMatchers("/otp").permitAll()
					.requestMatchers("/confirmotp").permitAll()
					.requestMatchers("/resetpassword").permitAll()
					.requestMatchers("/resetcomplete").permitAll()
					.anyRequest().authenticated())
			.formLogin(form -> form
							.loginPage("/login")
							.loginProcessingUrl("/authenticate")
							.defaultSuccessUrl("/")
							.failureHandler(disabledAccountFailureHandler)
							.permitAll())
		  	.logout(logout -> logout.permitAll())
		  	.exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));
		return http.build();
	}
}
