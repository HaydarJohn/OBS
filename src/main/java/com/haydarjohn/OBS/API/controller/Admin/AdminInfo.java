package com.haydarjohn.OBS.API.controller.Admin;


import org.springframework.web.bind.annotation.*;

@RestController
public class AdminInfo {
    @GetMapping("/pages/admin/admin-info")
    public String adminInfo()
    {
        return "Admin Info"; // TODO get from database
    }

    @PostMapping("/pages/admin/admin-info")
    public void setAdminInfo(@RequestBody AdminInfo adminInfo)
    {
        // TODO Post to database
    }
}
