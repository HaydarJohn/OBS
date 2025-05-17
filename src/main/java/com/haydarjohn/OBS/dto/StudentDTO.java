package com.haydarjohn.OBS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNum;
    private String address;
    private LocalDate dateOfBirth;
    private Character gender;
    private String tckn;
    private String major;
    private LocalDate enrollmentDate;
    private LocalDate graduationDate;
    private String status;
    private String advisorId;
} 