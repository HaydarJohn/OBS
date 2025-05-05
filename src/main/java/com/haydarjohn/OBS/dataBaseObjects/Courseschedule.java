package com.haydarjohn.OBS.dataBaseObjects;

import jakarta.persistence.*;

@Entity
@Table(name = "courseschedule", schema = "obs_database", indexes = {
        @Index(name = "course_id", columnList = "course_id"),
        @Index(name = "classroom_id", columnList = "classroom_id")
})
public class Courseschedule {
    @Id
    @Column(name = "course_schedule_id", nullable = false, length = 10)
    private String courseScheduleId;

    @Column(name = "course_id", nullable = false, length = 10)
    private String courseId;

    @Column(name = "classroom_id", nullable = false, length = 10)
    private String classroomId;

    @Column(name = "day_of_week", nullable = false, length = 10)
    private String dayOfWeek;

    @Column(name = "start_time", nullable = false, length = 10)
    private String startTime;

    @Column(name = "end_time", nullable = false, length = 10)
    private String endTime;

    public String getCourseScheduleId() {
        return courseScheduleId;
    }

    public void setCourseScheduleId(String courseScheduleId) {
        this.courseScheduleId = courseScheduleId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}