package com.haydarjohn.OBS.API.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrolledCourses {


    @GetMapping("/pages/student/enrolled-courses")
    public String getEnrolledCourses(String StudentID)
    {
        return "enrolled-courses"; // TODO get from database;
    }
}
