package com.haydarjohn.OBS.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "grade", schema = "obs_database", indexes = {
        @Index(name = "enrollment_id", columnList = "enrollment_id")
})
public class Grade {
    @Id
    @Column(name = "grade_id", nullable = false, length = 10)
    private String gradeId;

    @Column(name = "enrollment_id", nullable = false)
    private Integer enrollmentId;

    @Column(name = "midterm_score")
    private Integer midtermScore;

    @Column(name = "final_score")
    private Integer finalScore;

    @Column(name = "assignment_score")
    private Integer assignmentScore;

    @Column(name = "participation_score")
    private Integer participationScore;

    @Column(name = "total_grade", length = 5)
    private String totalGrade;

    @Column(name = "last_updated")
    private LocalDate lastUpdated;

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Integer getMidtermScore() {
        return midtermScore;
    }

    public void setMidtermScore(Integer midtermScore) {
        this.midtermScore = midtermScore;
    }

    public Integer getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }

    public Integer getAssignmentScore() {
        return assignmentScore;
    }

    public void setAssignmentScore(Integer assignmentScore) {
        this.assignmentScore = assignmentScore;
    }

    public Integer getParticipationScore() {
        return participationScore;
    }

    public void setParticipationScore(Integer participationScore) {
        this.participationScore = participationScore;
    }

    public String getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(String totalGrade) {
        this.totalGrade = totalGrade;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}