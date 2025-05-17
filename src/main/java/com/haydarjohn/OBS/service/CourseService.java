package com.haydarjohn.OBS.service;

import com.haydarjohn.OBS.dto.CourseDTO;
import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();
    CourseDTO findById(Long id);
    CourseDTO save(CourseDTO courseDTO);
    CourseDTO update(CourseDTO courseDTO);
    void deleteById(Long id);
} 