package com.haydarjohn.OBS.API.controller.Student;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrolledCourses {


    @GetMapping("/pages/student/enrolled-courses")
    public String getEnrolledCourses(@RequestBody String StudentID)
    {
        return "enrolled-courses"; // TODO get from database;
    }
}
