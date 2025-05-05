package com.haydarjohn.OBS.API.model;

import jakarta.persistence.Column;

public class Admin extends User {
    private String role;
    private String permissions;

    public Admin(String ID, String firstName, String lastName, String email, String password)
    {
        super(ID, firstName, lastName, email, password);
    }
}
