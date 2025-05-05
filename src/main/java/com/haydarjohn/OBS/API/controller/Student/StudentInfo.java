package com.haydarjohn.OBS.API.controller.Student;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentInfo {
    @GetMapping("/pages/student/student-info")
    public String studentInfo(String StudentID)
    {
        return "StudentInfo"; // TODO  get from database
    }

    @PostMapping("/pages/student/student-info")
    public void setInfo(@RequestBody String Info)
    {
        // TODO save to database
    }
}
