package com.haydarjohn.OBS.API.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentInfo {
    @GetMapping("/pages/student/student-info")
    public String studentInfo(String StudentID)
    {
        return "StudentInfo"; // TODO  get from database
    }
}
