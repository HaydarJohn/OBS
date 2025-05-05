package com.haydarjohn.OBS.API.controller.Teacher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherInfo
{
    @GetMapping("/pages/teacher/teacher-info")
    public String getTeacherInfo(@RequestBody String TeacherID)
    {
        return ""; // TODO Get from database
    }

    @PostMapping("/pages/teacher/teacher-info")
    public void setTeacherInfo(@RequestBody String info)
    {
        // TODO set database
    }
}
