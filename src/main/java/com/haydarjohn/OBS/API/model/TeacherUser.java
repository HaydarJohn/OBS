package com.haydarjohn.OBS.API.model;


import java.time.LocalDate;

public class Teacher extends User {

    private String phoneNum;
    private String address;
    private String officeLocation;
    private String department;
    private LocalDate hireDate;
    private Integer salary;

    public Teacher(String ID, String firstName, String lastName, String email, String password)
    {
        super(ID, firstName, lastName, email, password);
    }
}
