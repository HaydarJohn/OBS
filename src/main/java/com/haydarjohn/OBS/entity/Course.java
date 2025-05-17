package com.haydarjohn.OBS.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course", schema = "obs_database", indexes = {
        @Index(name = "teacher_id", columnList = "teacher_id")
})
public class Course {
    @Id
    @Column(name = "course_id", nullable = false, length = 10)
    private String courseId;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "code", nullable = false, length = 10)
    private String code;

    @Column(name = "credits", nullable = false)
    private Integer credits;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "department", length = 50)
    private String department;

    @Column(name = "prerequisites", length = 50)
    private String prerequisites;

    @Column(name = "max_capacity")
    private Integer maxCapacity;

    @Column(name = "current_enrollment")
    private Integer currentEnrollment;

    @Column(name = "semester_offered", length = 10)
    private String semesterOffered;

    @Column(name = "teacher_id", length = 10)
    private String teacherId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getCurrentEnrollment() {
        return currentEnrollment;
    }

    public void setCurrentEnrollment(Integer currentEnrollment) {
        this.currentEnrollment = currentEnrollment;
    }

    public String getSemesterOffered() {
        return semesterOffered;
    }

    public void setSemesterOffered(String semesterOffered) {
        this.semesterOffered = semesterOffered;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

}