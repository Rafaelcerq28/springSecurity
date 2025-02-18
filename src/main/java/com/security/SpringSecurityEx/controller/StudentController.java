package com.security.SpringSecurityEx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.SpringSecurityEx.Model.Student;

@RestController
public class StudentController {


    private ArrayList<Student> students = new ArrayList<>(List.of(
        new Student(1, "aluno A", 10),
        new Student(1, "aluno B", 10),
        new Student(1, "aluno C", 10)
    ));

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

}
