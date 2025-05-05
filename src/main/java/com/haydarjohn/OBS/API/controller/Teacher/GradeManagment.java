package com.haydarjohn.OBS.API.controller.Teacher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GradeManagment
{
    @GetMapping("/pages/teacher/grade-managment")
    public String getGrades(@RequestBody String TeacherID)
    {
        return "";// TODO Get from database;
    }

    @PostMapping("/pages/teacher/grade-managment")
    public void setGrade(@RequestBody String IDK_whattodo)
    {
        // TODO set database
    }
}
