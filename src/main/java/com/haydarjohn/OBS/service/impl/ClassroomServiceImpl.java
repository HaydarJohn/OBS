package com.haydarjohn.OBS.service.impl;

import com.haydarjohn.OBS.dto.ClassroomDTO;
import com.haydarjohn.OBS.entity.Classroom;
import com.haydarjohn.OBS.repository.ClassroomRepository;
import com.haydarjohn.OBS.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public List<ClassroomDTO> findAll() {
        return classroomRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClassroomDTO findById(String id) {
        return classroomRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Classroom not found with id: " + id));
    }

    @Override
    public ClassroomDTO save(ClassroomDTO classroomDTO) {
        Classroom classroom = convertToEntity(classroomDTO);
        Classroom savedClassroom = classroomRepository.save(classroom);
        return convertToDTO(savedClassroom);
    }

    @Override
    public ClassroomDTO update(ClassroomDTO classroomDTO) {
        if (!classroomRepository.existsById(classroomDTO.getId())) {
            throw new RuntimeException("Classroom not found with id: " + classroomDTO.getId());
        }
        Classroom classroom = convertToEntity(classroomDTO);
        Classroom updatedClassroom = classroomRepository.save(classroom);
        return convertToDTO(updatedClassroom);
    }

    @Override
    public void deleteById(String id) {
        if (!classroomRepository.existsById(id)) {
            throw new RuntimeException("Classroom not found with id: " + id);
        }
        classroomRepository.deleteById(id);
    }

    private ClassroomDTO convertToDTO(Classroom classroom) {
        ClassroomDTO dto = new ClassroomDTO();
        dto.setId(classroom.getId());
        dto.setCapacity(classroom.getCapacity());
        dto.setBuilding(classroom.getBuilding());
        return dto;
    }

    private Classroom convertToEntity(ClassroomDTO dto) {
        Classroom classroom = new Classroom();
        classroom.setId(dto.getId());
        classroom.setCapacity(dto.getCapacity());
        classroom.setBuilding(dto.getBuilding());
        return classroom;
    }
} 