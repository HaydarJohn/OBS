package com.haydarjohn.OBS.API.service;


import netscape.javascript.JSObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student/academic_summary")
public class AcademicSummary
{
    @GetMapping
    public String[] getMainPage(@RequestBody String studentId)
    {
        String[] result = new String[4];
        result[0] = getGPA(studentId);                   // TODO:
        result[1] = getTotoalCredits(studentId);         // TODO:
        result[2] = getCompletedCourseNum(studentId);    // TODO:
        result[3] = getAdvisorName(studentId);           // TODO:
    }

    public String getGPA(String studentId)
    {

    }
}
