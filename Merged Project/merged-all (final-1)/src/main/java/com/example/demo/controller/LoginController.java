package com.example.demo.controller;

import com.example.demo.data.UserRepository;
import com.example.demo.entity.*;
import com.example.demo.service.UserService;
import com.example.demo.util.EmailService;
import com.example.demo.util.OtpUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;
import java.time.LocalDateTime;

@Controller
public class LoginController {

	UserRepository ur;
	EmailService es;
	OtpUtil ou;
	UserService us;

	@Autowired
	public LoginController(UserRepository ur, EmailService es, OtpUtil ou, UserService us) {
		this.ur = ur;
		this.es = es;
		this.ou = ou;
		this.us = us;
	}

	@GetMapping("/")
	public String redirectBasedOnRole(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// Checking roles
		for (GrantedAuthority authority : auth.getAuthorities()) {
			if (authority.getAuthority().equals("ROLE_ADMIN")) {
				return "redirect:/admin/home";
			} else if (authority.getAuthority().equals("ROLE_MANAGER")) {
				return "redirect:/test/homepage";
			} else if (authority.getAuthority().equals("ROLE_TEACHER")) {
				return "redirect:/teacher/homepage";
			} else if (authority.getAuthority().equals("ROLE_STUDENT")) {
				return "redirect:/student/home";
			}
		}

		// Default redirection if role not found
		return "redirect:/access-denied";
	}

	@GetMapping("/logout")
	public String autoLogout(){
		return "security/auto-logout";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "security/login";
	}

	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "security/access-denied";
	}

	@GetMapping("/changepassword")
	public String loadChangePassword(){
		return "security/change-password";
	}

	@PostMapping("/changepassword")
	public String processChangePassword(@RequestParam("old") String op, @RequestParam("new") String np, @RequestParam("confirm") String cp, HttpSession session){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User u = ur.findByUsername(name);
		String oldRaw = u.getRawPassword();
		String old = u.getPassword();
		if(oldRaw.startsWith("{bcrypt}") && !BCrypt.checkpw(op,old)) return "redirect:/changepassword?incorrect";
		if(oldRaw.startsWith("{noop}") && !old.equals(op)) return "redirect:/changepassword?incorrect";
		if(!np.equals(cp)) return "redirect:/changepassword?mismatch";
		else u.setPassword(np);
		session.setAttribute("logged_user",u);
		us.save(u,"{bcrypt}");
		return "redirect:/";
	}

	@GetMapping("/forgotpassword")
	public String loadForgotPassword(){
		return "security/forgot-password";
	}

	@PostMapping("/forgotpassword")
	public String forgotPassword(@RequestParam(value = "email", required = false) String email, RedirectAttributes ra, HttpServletResponse response, @ModelAttribute("reset_user") User u){
		if(u.getUserId()==null) u = ur.findByUseremail(email);
		if(u==null) return "redirect:/forgotpassword?incorrect";
		else {
// send otp email here
			try {
				ou.newOtp();
				es.sendOtp(u, ou.getString());
				ra.addAttribute("reset_user", u);
//				System.out.println("--------------------------- User before redirecting to OTP page: " + u);
				return "redirect:/confirmotp";
			}catch(MessagingException e){
//				System.out.println("Mail error.");
				return "redirect:/confirmotp";
			}
		}
	}

	@GetMapping("/confirmotp")
	public String loadConfirmOtp(@ModelAttribute("reset_user") User u){
//		System.out.println("--------------------------- User after redirecting to OTP page: " + u);
		return "security/confirm-otp";
	}

	@PostMapping("/confirmotp")
	public String confirmOtp(@RequestParam("otp") String otp, @ModelAttribute("reset_user") User u, RedirectAttributes ra, HttpServletRequest request){
//		System.out.println("--------------------------- User after processing OTP: " + u);
		ra.addAttribute("reset_user",u);
//		otp logic here
		if(Duration.between(ou.getCreationTime(),LocalDateTime.now()).getSeconds() > 60) return "redirect:/confirmotp?expired";
		if(otp.equals(ou.getString())) {
//			System.out.println("--------------------------- User before redirecting to reset password page: " + u);
			return "redirect:/resetpassword";
		}
		else return "redirect:/confirmotp?incorrect";
	}

	@GetMapping("/resetpassword")
	public String loadResetPassword(@ModelAttribute("reset_user") User u){
//		System.out.println("--------------------------- User after redirecting to reset password page: " + u);
		return "security/reset-password";
	}

	@PostMapping("/resetpassword")
	public String resetPassword(@RequestParam("newpw") String newPw, @RequestParam("confirmpw") String confirmPw, @ModelAttribute("reset_user") User u, RedirectAttributes ra, HttpServletRequest request, HttpServletResponse response){
		if(!newPw.equals(confirmPw)){
			ra.addAttribute("reset_user",u);
			return "redirect:/resetpassword?mismatch";
		}
		else {
			u.setPassword(newPw);
			us.save(u,"{bcrypt}");
			return "redirect:/resetcomplete";
		}
	}

	@GetMapping("/resetcomplete")
	public String resetComplete(){
		return "security/reset-complete";
	}
}
