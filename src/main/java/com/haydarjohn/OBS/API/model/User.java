package com.haydarjohn.OBS.API.model;

import com.haydarjohn.OBS.dataBaseObjects.Administrator;
import com.haydarjohn.OBS.dataBaseObjects.Student;
import com.haydarjohn.OBS.dataBaseObjects.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class User {
    protected String ID;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;

    private String userType;

    public User(String ID, String firstName, String lastName, String email, String password)
    {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

        this.userType = "Unknown";
    }
    @Autowired
    public User(Student student)
    {
        this.ID = student.getStudentId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.password = student.getPassword();

        this.userType = "Student";
    }
    public User(Teacher teacher)
    {
        this.ID = teacher.getTeacherId();
        this.firstName = teacher.getFirstName();
        this.lastName = teacher.getLastName();
        this.email = teacher.getEmail();
        this.password = teacher.getPassword();

        this.userType = "Teacher";
    }
    public User(Administrator administrator)
    {
        this.ID = administrator.getAdminId();
        this.firstName = administrator.getFirstName();
        this.lastName = administrator.getLastName();
        this.email = administrator.getEmail();
        this.password = administrator.getPassword();

        this.userType = "Administrator";
    }


    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
