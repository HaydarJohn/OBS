package com.haydarjohn.OBS.API.controller.Student;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Grades {
    @GetMapping("/pages/student/grades")
    public String Grades(@RequestBody String StudentID)
    {
        return ""; // TODO get from database
    }
}
