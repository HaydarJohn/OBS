package com.haydarjohn.OBS.API.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Grades {
    @GetMapping("/pages/student/grades")
    public String Grades()
    {
        return ""; // TODO get from database
    }
}
