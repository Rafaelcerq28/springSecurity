package com.security.SpringSecurityEx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.SpringSecurityEx.Model.Student;

import jakarta.servlet.http.HttpServletRequest;

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

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        students.add(student);

        return student;
    }

    //How to get CSFR Token

    /*
     Return ex:   
    {
        "parameterName": "_csrf",
        "token": "939cqFlKyjN1bjHpMF8LEUGfizyDx6vdUSwAge6RN2L5r5m5zkdlyTp4qwFYWVWNAXI_cHn8pl27_s_wYE5hsdiiBFrMzvuL",
        "headerName": "X-CSRF-TOKEN"     
    }

    put the token and the headerName in the header to make our POST works
     */
    
    @GetMapping("/csrf-token")
    public CsrfToken csrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

}
