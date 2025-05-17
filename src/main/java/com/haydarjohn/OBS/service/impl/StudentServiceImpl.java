package com.haydarjohn.OBS.service.impl;

import com.haydarjohn.OBS.dto.StudentDTO;
import com.haydarjohn.OBS.entity.Student;
import com.haydarjohn.OBS.repository.StudentRepository;
import com.haydarjohn.OBS.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO findById(Long id) {
        return studentRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        if (!studentRepository.existsById(studentDTO.getId())) {
            throw new RuntimeException("Student not found with id: " + studentDTO.getId());
        }
        Student student = convertToEntity(studentDTO);
        Student updatedStudent = studentRepository.save(student);
        return convertToDTO(updatedStudent);
    }

    @Override
    public void deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    private StudentDTO convertToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setPassword(student.getPassword());
        dto.setPhoneNum(student.getPhoneNum());
        dto.setAddress(student.getAddress());
        dto.setDateOfBirth(student.getDateOfBirth());
        dto.setGender(student.getGender());
        dto.setTckn(student.getTckn());
        dto.setMajor(student.getMajor());
        dto.setEnrollmentDate(student.getEnrollmentDate());
        dto.setGraduationDate(student.getGraduationDate());
        dto.setStatus(student.getStatus());
        dto.setAdvisorId(student.getAdvisorId());
        return dto;
    }

    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPassword(dto.getPassword());
        student.setPhoneNum(dto.getPhoneNum());
        student.setAddress(dto.getAddress());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setGender(dto.getGender());
        student.setTckn(dto.getTckn());
        student.setMajor(dto.getMajor());
        student.setEnrollmentDate(dto.getEnrollmentDate());
        student.setGraduationDate(dto.getGraduationDate());
        student.setStatus(dto.getStatus());
        student.setAdvisorId(dto.getAdvisorId());
        return student;
    }
} 