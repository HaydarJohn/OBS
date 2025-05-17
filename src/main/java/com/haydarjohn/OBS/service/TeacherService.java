package com.haydarjohn.OBS.service;

import com.haydarjohn.OBS.dto.TeacherDTO;
import java.util.List;

public interface TeacherService {
    List<TeacherDTO> findAll();
    TeacherDTO findById(Long id);
    TeacherDTO save(TeacherDTO teacherDTO);
    TeacherDTO update(TeacherDTO teacherDTO);
    void deleteById(Long id);
} 