package com.haydarjohn.OBS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDTO {
    private Long id;
    private String name;
    private Integer capacity;
    private String building;
    private String floor;
} 