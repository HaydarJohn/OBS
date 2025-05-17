package com.haydarjohn.OBS.controller;

import com.haydarjohn.OBS.dto.CourseScheduleDTO;
import com.haydarjohn.OBS.service.CourseScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-schedules")
public class CourseScheduleController {

    private final CourseScheduleService courseScheduleService;

    @Autowired
    public CourseScheduleController(CourseScheduleService courseScheduleService) {
        this.courseScheduleService = courseScheduleService;
    }

    @GetMapping
    public ResponseEntity<List<CourseScheduleDTO>> getAllCourseSchedules() {
        return ResponseEntity.ok(courseScheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseScheduleDTO> getCourseScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(courseScheduleService.findById(String.valueOf(id)));
    }

    @PostMapping
    public ResponseEntity<CourseScheduleDTO> createCourseSchedule(@RequestBody CourseScheduleDTO courseScheduleDTO) {
        return ResponseEntity.ok(courseScheduleService.save(courseScheduleDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseScheduleDTO> updateCourseSchedule(@PathVariable Long id, @RequestBody CourseScheduleDTO courseScheduleDTO) {
        courseScheduleDTO.setId(String.valueOf(id));
        return ResponseEntity.ok(courseScheduleService.update(courseScheduleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseSchedule(@PathVariable Long id) {
        courseScheduleService.deleteById(String.valueOf(id));
        return ResponseEntity.ok().build();
    }
} 