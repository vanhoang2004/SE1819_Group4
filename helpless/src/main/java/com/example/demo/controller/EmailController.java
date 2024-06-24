package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.VerificationToken;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import com.example.demo.service.VerificationTokenService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/email")
public class EmailController {
    private final VerificationTokenService verificationTokenService;
    private final EmailService emailService;
    private final UserService userService;

    @Autowired
    public EmailController(VerificationTokenService verificationTokenService, EmailService emailService, UserService userService) {
        this.verificationTokenService = verificationTokenService;
        this.emailService = emailService;
        this.userService = userService;
    }

    @GetMapping("/activation")
    public String activation(@RequestParam("token") String token, Model model) {
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        if (verificationToken == null) {
            model.addAttribute("message", "Your verification token is invalid");
        } else {
            User user = verificationToken.getUser();
            // Activate the user account
            user.setUseremail(verificationToken.getNewEmail());
            userService.save(user); // Assuming saveUser method persists the user
            model.addAttribute("message", "Your account is successfully activated");
        }
        return "redirect:/showMyLoginPage"; // The name of the view to return
    }

    @PostMapping("/submitEmail")
    public String submitEmail(@RequestParam("email") String email, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userService.findUserByName(username);
        if (user != null) {
            try {
                VerificationToken token = verificationTokenService.createVerificationToken(user, email);
                emailService.sendHtmlMail(user, token);
                model.addAttribute("message", "Verification email sent successfully.");
                //user.setUseremail(email);
            } catch (MessagingException e) {
                model.addAttribute("message", "Error sending email.");
                e.printStackTrace();
            }
        } else {
            model.addAttribute("message", "User not found.");
        }

        return "redirect:/teacher/homepage"; // The name of the view to return (e.g., a confirmation page)
    }
}
