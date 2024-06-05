package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@GetMapping("/")
	public String redirectBasedOnRole(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String name = auth.getName();
//		User user= userRepository.findUserByName(name);
//		Integer userid = user.getId();


		// Checking roles
		for (GrantedAuthority authority : auth.getAuthorities()) {
			if (authority.getAuthority().equals("ROLE_ADMIN")) {
				return "redirect:/admin/home";
			} else if (authority.getAuthority().equals("ROLE_MANAGER")) {
				return "redirect:/test/homepage";
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