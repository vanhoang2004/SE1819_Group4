package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.VerificationToken;
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
    private final VerificationTokenService verificationTokenService;
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

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
}
