package com.haydarjohn.OBS.service.impl;

import com.haydarjohn.OBS.dto.CourseScheduleDTO;
import com.haydarjohn.OBS.entity.CourseSchedule;
import com.haydarjohn.OBS.repository.CourseScheduleRepository;
import com.haydarjohn.OBS.service.CourseScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseScheduleServiceImpl implements CourseScheduleService {
    
    private final CourseScheduleRepository courseScheduleRepository;

    @Autowired
    public CourseScheduleServiceImpl(CourseScheduleRepository courseScheduleRepository) {
        this.courseScheduleRepository = courseScheduleRepository;
    }

    @Override
    public List<CourseScheduleDTO> findAll() {
        return courseScheduleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseScheduleDTO findById(String id) {
        return courseScheduleRepository.findById(Long.valueOf(id))
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Course Schedule not found with id: " + id));
    }

    @Override
    public CourseScheduleDTO save(CourseScheduleDTO courseScheduleDTO) {
        CourseSchedule courseSchedule = convertToEntity(courseScheduleDTO);
        CourseSchedule savedCourseSchedule = courseScheduleRepository.save(courseSchedule);
        return convertToDTO(savedCourseSchedule);
    }

    @Override
    public CourseScheduleDTO update(CourseScheduleDTO courseScheduleDTO) {
        if (!courseScheduleRepository.existsById(Long.valueOf(courseScheduleDTO.getId()))) {
            throw new RuntimeException("Course Schedule not found with id: " + courseScheduleDTO.getId());
        }
        CourseSchedule courseSchedule = convertToEntity(courseScheduleDTO);
        CourseSchedule updatedCourseSchedule = courseScheduleRepository.save(courseSchedule);
        return convertToDTO(updatedCourseSchedule);
    }

    @Override
    public void deleteById(String id) {
        if (!courseScheduleRepository.existsById(Long.valueOf(id))) {
            throw new RuntimeException("Course Schedule not found with id: " + id);
        }
        courseScheduleRepository.deleteById(Long.valueOf(id));
    }

    private CourseScheduleDTO convertToDTO(CourseSchedule courseSchedule) {
        CourseScheduleDTO dto = new CourseScheduleDTO();
        dto.setId(courseSchedule.getCourseScheduleId());
        dto.setCourseId(Long.parseLong(courseSchedule.getCourseId()));
        dto.setClassroomId(Long.parseLong(courseSchedule.getClassroomId()));
        dto.setDayOfWeek(DayOfWeek.valueOf(courseSchedule.getDayOfWeek().toUpperCase()));
        dto.setStartTime(LocalTime.parse(courseSchedule.getStartTime()));
        dto.setEndTime(LocalTime.parse(courseSchedule.getEndTime()));
        return dto;
    }

    private CourseSchedule convertToEntity(CourseScheduleDTO dto) {
        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setCourseScheduleId(dto.getId());
        courseSchedule.setCourseId(String.valueOf(dto.getCourseId()));
        courseSchedule.setClassroomId(String.valueOf(dto.getClassroomId()));
        courseSchedule.setDayOfWeek(dto.getDayOfWeek().toString());
        courseSchedule.setStartTime(dto.getStartTime().toString());
        courseSchedule.setEndTime(dto.getEndTime().toString());
        return courseSchedule;
    }
} 