package com.haydarjohn.OBS.service;

import com.haydarjohn.OBS.dto.StudentDTO;
import java.util.List;

public interface StudentService {
    List<StudentDTO> findAll();
    StudentDTO findById(Long id);
    StudentDTO save(StudentDTO studentDTO);
    StudentDTO update(StudentDTO studentDTO);
    void deleteById(Long id);
} 