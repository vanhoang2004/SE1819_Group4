package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/")
	public String redirectBasedOnRole() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// Checking roles
		for (GrantedAuthority authority : auth.getAuthorities()) {
			if (authority.getAuthority().equals("ROLE_ADMIN")) {
				return "redirect:/admin/home";
			} else if (authority.getAuthority().equals("ROLE_MANAGER")) {
				return "redirect:/manager/home";
			} else if (authority.getAuthority().equals("ROLE_TEACHER")) {
				return "redirect:/teacher/home";
			} else if (authority.getAuthority().equals("ROLE_STUDENT")) {
				return "redirect:/student/home";
			}
		}

		// Default redirection if role not found
		return "redirect:/access-denied";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "fancy-login";
	}
	
	//add request mapping for /access-denied
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}
}
