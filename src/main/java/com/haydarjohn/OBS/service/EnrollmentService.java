package com.haydarjohn.OBS.service;

import com.haydarjohn.OBS.dto.EnrollmentDTO;
import java.util.List;

public interface EnrollmentService {
    List<EnrollmentDTO> findAll();
    EnrollmentDTO findById(Long id);
    EnrollmentDTO save(EnrollmentDTO enrollmentDTO);
    EnrollmentDTO update(EnrollmentDTO enrollmentDTO);
    void deleteById(Long id);
} 