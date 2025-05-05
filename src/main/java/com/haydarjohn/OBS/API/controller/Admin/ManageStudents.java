package com.haydarjohn.OBS.API.controller.Admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManageStudents
{
    @GetMapping("/pages/admin/manage-students")
    public String geStudentList(@RequestBody String AdminID)
    {
        return "";
        // TODO Get from database
    }
    @PostMapping("/pages/admin/manage-students")
    public void setStudentList(@RequestBody String info)
    {
        // If delete
        // deletStudent()
        // else modify
    }

}
