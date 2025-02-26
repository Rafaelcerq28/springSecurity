package com.security.SpringSecurityEx.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.SpringSecurityEx.Model.UserPrincipal;
import com.security.SpringSecurityEx.Model.Users;
import com.security.SpringSecurityEx.repository.UserRepo;

//Class to implement UserDetailsService interface
//This class is used to load user-specific data
@Service
public class MyUserDetailsService implements UserDetailsService{

    private UserRepo userRepo;

    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //This method is used to load user-specific data
    //It is used by Spring Security to load user-specific data
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepo.findByUsername(username);
        //If user is not found, throw an exception
        if(user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        //Return the UserPrincipal object (which implements UserDetails interface)
        return new UserPrincipal(user);
    }

}
