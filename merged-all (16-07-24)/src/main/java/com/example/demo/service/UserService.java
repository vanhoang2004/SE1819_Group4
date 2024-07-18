package com.example.demo.service;

import com.example.demo.data.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByName(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
