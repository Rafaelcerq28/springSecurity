package com.security.SpringSecurityEx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.SpringSecurityEx.Model.Student;
import com.security.SpringSecurityEx.Model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);

}
