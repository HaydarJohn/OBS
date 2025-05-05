package com.haydarjohn.OBS.API.model;

public class AdminUser extends User {
    private String role;
    private String permissions;

    public AdminUser(String ID, String firstName, String lastName, String email, String password)
    {
        super(ID, firstName, lastName, email, password);
    }
}
