package com.haydarjohn.OBS.API.controller.Teacher;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyCourses {
    @GetMapping("/pages/teacher/mycourses")
    public String getCourses(@RequestBody String TeacherID)
    {
        return ""; // TODO Get from database
    }
}
