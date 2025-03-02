package com.security.SpringSecurityEx.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.SpringSecurityEx.Model.Users;
import com.security.SpringSecurityEx.repository.UserRepo;

@Service
public class UserService {

    private UserRepo userRepo;
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;

    // BCryptPasswordEncoder is a Spring Security class used to encrypt passwords
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepo userRepo, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    
    // Registers a new user
    public Users register(Users user) {
        // Encrypts the user's password before saving it to the database
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String verify(Users user) {
        // Authenticates the user, returning an Authentication object if successful
        Authentication authentication = 
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        
        // If the user is authenticated, generate a JWT token
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }

        return "failure";
    }

}
