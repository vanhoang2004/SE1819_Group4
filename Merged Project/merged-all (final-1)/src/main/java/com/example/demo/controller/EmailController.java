package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.VerificationToken;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.util.EmailService;
import com.example.demo.service.EmailVerificationService;
import com.example.demo.service.UserService;
import com.example.demo.service.VerificationTokenService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/email")
public class EmailController {
    private final VerificationTokenService verificationTokenService;
    private final EmailService emailService;
    private final UserService userService;
    private final EmailVerificationService emailVerificationService;

    @Autowired
    public EmailController(VerificationTokenService verificationTokenService,
                           EmailService emailService,
                           UserService userService,
                           EmailVerificationService emailVerificationService
    ) {
        this.verificationTokenService = verificationTokenService;
        this.emailService = emailService;
        this.userService = userService;
        this.emailVerificationService = emailVerificationService;
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
            userService.save(user, ""); // Assuming saveUser method persists the user
            model.addAttribute("message", "Your account is successfully activated");
        }
        return "redirect:/login"; // The name of the view to return
    }

    @PostMapping("/submitEmail")
    public String submitEmail(@RequestParam("email") String email, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userService.findUserByName(username);
        if (user != null) {
            if (emailVerificationService.verifyEmail(email)) {
                try {
                    VerificationToken token = verificationTokenService.createVerificationToken(user, email);
                    emailService.sendHtmlMail(user, token);
                    model.addAttribute("message", "Verification email sent successfully.");
                    return "redirect:/login"; // Redirect to homepage
                } catch (MessagingException e) {
                    model.addAttribute("message", "Error sending email.");
                    e.printStackTrace();
                    return "error"; // Return an error view
                }
            } else {
                throw new ApiRequestException("Email does not exist.");
                //return "error"; // Return an error view
            }
        } else {
            model.addAttribute("message", "User not found.");
            return "error"; // Return an error view
        }
    }
}
