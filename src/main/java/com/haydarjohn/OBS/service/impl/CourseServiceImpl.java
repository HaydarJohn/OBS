package com.haydarjohn.OBS.service.impl;

import com.haydarjohn.OBS.dto.CourseDTO;
import com.haydarjohn.OBS.entity.Course;
import com.haydarjohn.OBS.repository.CourseRepository;
import com.haydarjohn.OBS.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO findById(Long id) {
        return courseRepository.findById(String.valueOf(id))
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    @Override
    public CourseDTO update(CourseDTO courseDTO) {
        if (!courseRepository.existsById(String.valueOf(courseDTO.getId()))) {
            throw new RuntimeException("Course not found with id: " + courseDTO.getId());
        }
        Course course = convertToEntity(courseDTO);
        Course updatedCourse = courseRepository.save(course);
        return convertToDTO(updatedCourse);
    }

    @Override
    public void deleteById(Long id) {
        if (!courseRepository.existsById(String.valueOf(id))) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        courseRepository.deleteById(String.valueOf(id));
    }

    private CourseDTO convertToDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(Long.parseLong(course.getCourseId()));
        dto.setCode(course.getCode());
        dto.setName(course.getTitle());
        dto.setCredit(course.getCredits());
        dto.setDepartmentId(course.getDepartment() != null ? Long.parseLong(course.getDepartment()) : null);
        dto.setTeacherId(course.getTeacherId() != null ? Long.parseLong(course.getTeacherId()) : null);
        return dto;
    }

    private Course convertToEntity(CourseDTO dto) {
        Course course = new Course();
        course.setCourseId(String.valueOf(dto.getId()));
        course.setCode(dto.getCode());
        course.setTitle(dto.getName());
        course.setCredits(dto.getCredit());
        course.setDepartment(dto.getDepartmentId() != null ? String.valueOf(dto.getDepartmentId()) : null);
        course.setTeacherId(dto.getTeacherId() != null ? String.valueOf(dto.getTeacherId()) : null);
        return course;
    }
} 