package com.example.demo.controller;

import com.example.demo.data.UserRepository;
import com.example.demo.entity.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

	@Autowired
	UserRepository ur;

	@GetMapping("/")
	public String redirectBasedOnRole(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated()) {
			System.out.println(auth.getPrincipal());
			String userDetails = (String) auth.getPrincipal();
			request.getSession().setAttribute("logged_user", ur.findUserByUsername(userDetails));
		}

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

	@GetMapping("/changepassword")
	public  String changepass(Model model){
		model.addAttribute("change", new ChangePass());
		return "changepassword";
	}

	@PostMapping("/changepassword")
	public String changePassWord(@ModelAttribute("change") ChangePass change, Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user = ur.findUserByUsername(name);

		if(!user.getPassword().equals("{noop}"+change.getOldpassword())){
			model.addAttribute("error","Incorrect Password");
			return "changepassword";
		}
		else
		if(!change.getNewpassword().equals(change.getReenter())){
			model.addAttribute("error","Password does not match");
			return "changepassword";
		}
		user.setPassword("{noop}"+change.getNewpassword());
		ur.save(user);
		return "redirect:/test/homepage";
	}
}
