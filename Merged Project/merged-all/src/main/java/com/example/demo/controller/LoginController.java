package com.example.demo.controller;

import com.example.demo.data.UserRepository;
import com.example.demo.entity.*;
import com.example.demo.util.EmailService;
import com.example.demo.util.OtpUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Autowired
	public LoginController(UserRepository ur, EmailService es, OtpUtil ou) {
		this.ur = ur;
		this.es = es;
		this.ou = ou;
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

	@GetMapping("/login")
	public String showLoginPage() {
		return "security/login";
	}
	
	//add request mapping for /access-denied
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "security/access-denied";
	}

	@GetMapping("/changepassword")
	public  String changepass(Model model){
		model.addAttribute("change", new ChangePass());
		return "security/changepassword";
	}

	@PostMapping("/changepassword")
	public String changePassWord(@ModelAttribute("change") ChangePass change, Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user = ur.findByUsername(name);

		if(!user.getPassword().equals("{noop}"+change.getOldpassword())){
			model.addAttribute("error","Incorrect Password");
			return "security/changepassword";
		}
		else
		if(!change.getNewpassword().equals(change.getReenter())){
			model.addAttribute("error","Password does not match");
			return "security/changepassword";
		}
		user.setPassword("{noop}"+change.getNewpassword());
		ur.save(user);
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
				System.out.println("--------------------------- User before redirecting to OTP page: " + u);
				return "redirect:/confirmotp";
			}catch(MessagingException e){
				System.out.println("Mail error.");
				return "redirect:/confirmotp";
			}
		}
	}

	@GetMapping("/confirmotp")
	public String loadConfirmOtp(@ModelAttribute("reset_user") User u){
		System.out.println("--------------------------- User after redirecting to OTP page: " + u);
		return "security/confirm-otp";
	}

	@PostMapping("/confirmotp")
	public String confirmOtp(@RequestParam("otp") String otp, @ModelAttribute("reset_user") User u, RedirectAttributes ra, HttpServletRequest request){
		System.out.println("--------------------------- User after processing OTP: " + u);
		ra.addAttribute("reset_user",u);
//		otp logic here
		if(Duration.between(ou.getCreationTime(),LocalDateTime.now()).getSeconds() > 60) return "redirect:/confirmotp?expired";
		if(otp.equals(ou.getString())) {
			System.out.println("--------------------------- User before redirecting to reset password page: " + u);
			return "redirect:/resetpassword";
		}
		else return "redirect:/confirmotp?incorrect";
	}

	@GetMapping("/resetpassword")
	public String loadResetPassword(@ModelAttribute("reset_user") User u){
		System.out.println("--------------------------- User after redirecting to reset password page: " + u);
		return "security/reset-password";
	}

	@PostMapping("/resetpassword")
	public String resetPassword(@RequestParam("newpw") String newPw, @RequestParam("confirmpw") String confirmPw, @ModelAttribute("reset_user") User u, RedirectAttributes ra, HttpServletRequest request, HttpServletResponse response){
		if(!newPw.equals(confirmPw)){
			ra.addAttribute("reset_user",u);
			return "redirect:/resetpassword?notmatch";
		}
		else {
			u.setPassword(newPw);
			ur.save(u);
			return "redirect:/resetcomplete";
		}
	}

	@GetMapping("/resetcomplete")
	public String resetComplete(){
		return "security/reset-complete";
	}
}
