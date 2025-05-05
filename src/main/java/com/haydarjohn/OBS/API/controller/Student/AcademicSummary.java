package com.haydarjohn.OBS.API.controller.Student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AcademicSummary {

    @GetMapping("/pages/student/academic-summary")
    public String StudentPage(String StudentID)
    {
        //TODO:Calculate GPA
        //TODO:Get Total Credits
        //TODO:Get FInished Courses
        //TODO:Get AdvisorName


        return Double.toString(calcGPA(StudentID)) + ","
                + Double.toString(getTotolCreds(StudentID)) + ","
                + getCompletedCourses(StudentID) + ","
                + getAdvisor(StudentID);// TODO:Fix

    }


    public double calcGPA(String StudentID)
    {
        // TODO: Get Total grades finised;
        double grade = 0;// = getGrades form database
        double creds =    getTotolCreds(StudentID);
        return grade / creds;
    }

    public double getTotolCreds(String StudentID)
    {
        // TODO: Database stuff
        return 0; //TODO fix
    }

    public String getCompletedCourses(String StudentID)
    {
        return ""; // TODO fix
    }
    public String getAdvisor(String StudentID)
    {
        return ""; //TODO fix
    }
}
