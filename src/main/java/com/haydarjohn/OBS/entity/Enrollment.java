package com.haydarjohn.OBS.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "enrollment", schema = "obs_database", indexes = {
        @Index(name = "student_id", columnList = "student_id"),
        @Index(name = "course_id", columnList = "course_id"),
        @Index(name = "semester_id", columnList = "semester_id")
})
public class Enrollment {
    @Id
    @Column(name = "enrollment_id", nullable = false)
    private Integer id;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Column(name = "course_id", nullable = false, length = 10)
    private String courseId;

    @Column(name = "semester_id", nullable = false, length = 10)
    private String semesterId;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    @Column(name = "drop_date", length = 20)
    private String dropDate;

    @Column(name = "status", length = 20)
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getDropDate() {
        return dropDate;
    }

    public void setDropDate(String dropDate) {
        this.dropDate = dropDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}