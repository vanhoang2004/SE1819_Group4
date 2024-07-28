package com.example.demo.service;

import com.example.demo.data.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public void save(User user, String encrypt) {
//        String raw = user.getRawPassword();
        if(encrypt.equals("{noop}")) user.setPassword(encrypt + user.getPassword());
        if(encrypt.equals("{bcrypt}")) {
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String encryptedPwd = bcrypt.encode(user.getPassword());
            user.setPassword(encrypt + encryptedPwd);
        }
//        if(encrypt.isEmpty()){
//            if(raw.startsWith("{noop}")) user.setPassword("{noop}" + user.getPassword());
//            if(raw.startsWith("{bcrypt}")) user.setPassword("{bcrypt}" + user.getPassword());
//        }
        userRepository.save(user);
    }
}
