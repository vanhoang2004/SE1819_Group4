package com.example.demo.util;

import com.example.demo.entity.User;
import com.example.demo.entity.VerificationToken;
import com.example.demo.service.VerificationTokenService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {
    private VerificationTokenService verificationTokenService;
    private TemplateEngine templateEngine;
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(VerificationTokenService verificationTokenService, TemplateEngine templateEngine,
                        JavaMailSender javaMailSender) {
        this.verificationTokenService = verificationTokenService;
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    public void sendHtmlMail(User user, VerificationToken token) throws MessagingException {
        Context context = new Context();
        context.setVariable("title", "Verify your email address");
        context.setVariable("link", "http://localhost:8080/email/activation?token=" + token.getToken());

        // Create an HTML template and pass the variable to it
        String body = templateEngine.process("mailverification", context);

        // Send the verification email
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(token.getNewEmail());
        helper.setSubject("Email address verification");
        helper.setText(body, true);
        javaMailSender.send(message);
    }

    public void sendOtp(User user, String otp) throws MessagingException {
        Context context = new Context();
        context.setVariable("otp",otp);

        // Create an HTML template and pass the variable to it
        String body = templateEngine.process("security/otp-mail", context);

        // Send the verification email
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject("Password Reset");
        helper.setText(body, true);
        helper.setTo(user.getUseremail());
        javaMailSender.send(message);
    }
}
