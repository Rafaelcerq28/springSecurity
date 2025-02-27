package com.security.SpringSecurityEx.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.SpringSecurityEx.Model.Users;
import com.security.SpringSecurityEx.repository.UserRepo;

@Service
public class UserService {

    private UserRepo userRepo;

    // BCryptPasswordEncoder is a Spring Security class used to encrypt passwords
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Users register(Users user) {
        // Encrypts the user's password before saving it to the database
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

}
