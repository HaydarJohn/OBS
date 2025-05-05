package com.haydarjohn.OBS.API.model;


import com.haydarjohn.OBS.dataBaseObjects.Student;

import java.time.LocalDate;

public class  StudentUser extends User {
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

    public StudentUser(String ID, String firstName, String lastName, String email, String password)
    {
        super(ID, firstName, lastName, email, password);
    }

    public StudentUser(Student student)
    {
        super(student.getStudentId(), student.getFirstName(), student.getLastName(), student.getEmail(), student.getPassword());
        this.phoneNumber = student.getPhoneNum();
        this.address = student.getAddress();
        this.birthDate = student.getDateOfBirth();
        this.gender = student.getGender();
        this.tckn = student.getTckn();
        this.major = student.getMajor();
        this.enrollmentDate = student.getEnrollmentDate();
        this.graduationDate = student.getGraduationDate();
        this.status = student.getStatus();
        this.advisorId = student.getAdvisorId();
    }
}
