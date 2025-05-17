package com.haydarjohn.OBS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    private Long id;
    private Double midtermGrade;
    private Double finalGrade;
    private Double averageGrade;
    private String letterGrade;
    private Long studentId;
    private Long courseId;
    private Long semesterId;
} 