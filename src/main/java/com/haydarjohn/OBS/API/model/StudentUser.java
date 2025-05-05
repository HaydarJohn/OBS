package com.haydarjohn.OBS.API.model;


import java.time.LocalDate;

public class Student extends User {
    private String phoneNumber;
    private String address;
    private LocalDate birthDate;
    private char gender;
    private String tckn;
    private String major;
    private LocalDate enrollmentDate;
    private LocalDate graduationDate;
    private String status;
    private String advisorId;

    public Student(String ID, String firstName, String lastName, String email, String password)
    {
        super(ID, firstName, lastName, email, password);
    }
}
