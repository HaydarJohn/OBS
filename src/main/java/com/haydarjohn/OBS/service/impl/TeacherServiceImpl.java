package com.haydarjohn.OBS.service.impl;

import com.haydarjohn.OBS.dto.TeacherDTO;
import com.haydarjohn.OBS.entity.Teacher;
import com.haydarjohn.OBS.repository.TeacherRepository;
import com.haydarjohn.OBS.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<TeacherDTO> findAll() {
        return teacherRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO findById(Long id) {
        return teacherRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
    }

    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) {
        Teacher teacher = convertToEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return convertToDTO(savedTeacher);
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO) {
        if (!teacherRepository.existsById(teacherDTO.getId())) {
            throw new RuntimeException("Teacher not found with id: " + teacherDTO.getId());
        }
        Teacher teacher = convertToEntity(teacherDTO);
        Teacher updatedTeacher = teacherRepository.save(teacher);
        return convertToDTO(updatedTeacher);
    }

    @Override
    public void deleteById(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
        teacherRepository.deleteById(id);
    }

    private TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        dto.setId(teacher.getTeacherId());
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setEmail(teacher.getEmail());
        dto.setPhoneNum(teacher.getPhoneNum());
        dto.setOfficeLocation(teacher.getOfficeLocation());
        dto.setDepartment(teacher.getDepartment());
        return dto;
    }

    private Teacher convertToEntity(TeacherDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(dto.getId());
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setEmail(dto.getEmail());
        teacher.setPhoneNum(dto.getPhoneNum());
        teacher.setOfficeLocation(dto.getOfficeLocation());
        teacher.setDepartment(dto.getDepartment());
        return teacher;
    }
} 