package com.haydarjohn.OBS.controller;

import com.haydarjohn.OBS.dto.ClassroomDTO;
import com.haydarjohn.OBS.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public ResponseEntity<List<ClassroomDTO>> getAllClassrooms() {
        return ResponseEntity.ok(classroomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDTO> getClassroomById(@PathVariable String id) {
        return ResponseEntity.ok(classroomService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClassroomDTO> createClassroom(@RequestBody ClassroomDTO classroomDTO) {
        return ResponseEntity.ok(classroomService.save(classroomDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomDTO> updateClassroom(@PathVariable String id, @RequestBody ClassroomDTO classroomDTO) {
        classroomDTO.setId(id);
        return ResponseEntity.ok(classroomService.update(classroomDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable String id) {
        classroomService.deleteById(id);
        return ResponseEntity.ok().build();
    }
} 