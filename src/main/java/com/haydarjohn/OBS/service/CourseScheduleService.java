package com.haydarjohn.OBS.service;

import com.haydarjohn.OBS.dto.CourseScheduleDTO;
import java.util.List;

public interface CourseScheduleService {
    List<CourseScheduleDTO> findAll();
    CourseScheduleDTO findById(String id);
    CourseScheduleDTO save(CourseScheduleDTO courseScheduleDTO);
    CourseScheduleDTO update(CourseScheduleDTO courseScheduleDTO);
    void deleteById(String id);
} 