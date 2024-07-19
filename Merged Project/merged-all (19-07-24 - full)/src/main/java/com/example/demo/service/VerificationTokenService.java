package com.example.demo.service;

import com.example.demo.data.VerificationRepository;
import com.example.demo.entity.User;
import com.example.demo.entity.VerificationToken;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class VerificationTokenService {
    private final VerificationRepository verificationRepository;

    @Autowired
    public VerificationTokenService(VerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    @Transactional
    public VerificationToken findByToken(String token) {
        return verificationRepository.findByToken(token);
    }

    @Transactional
    public VerificationToken findByUser(User user) {
        List<VerificationToken> tokens = verificationRepository.findByUser(user);
        if (tokens.isEmpty()) {
            return null;
        } else if (tokens.size() == 1) {
            return tokens.get(0);
        } else {
            throw new IllegalStateException("Multiple verification tokens found for user: " + user.getUserId());
        }
    }

    @Transactional
    public void deleteTokensByUser(User user) {
        verificationRepository.deleteByUser(user);
    }

    @Transactional
    public VerificationToken createVerificationToken(User user, String newEmail) {
        // Invalidate existing tokens
        deleteTokensByUser(user);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, user, newEmail);
        verificationRepository.save(verificationToken);
        return verificationToken;
    }

    // Calculate expiry date
    private Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Timestamp(cal.getTime().getTime());
    }
}
