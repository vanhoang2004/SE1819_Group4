package com.example.demo.data;

import com.example.demo.entity.User;
import com.example.demo.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
    List<VerificationToken> findByUser(User user);
    void deleteByUser(User user);
}
