package com.haydarjohn.OBS.service.impl;

import com.haydarjohn.OBS.dto.EnrollmentDTO;
import com.haydarjohn.OBS.entity.Enrollment;
import com.haydarjohn.OBS.repository.EnrollmentRepository;
import com.haydarjohn.OBS.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public List<EnrollmentDTO> findAll() {
        return enrollmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentDTO findById(Long id) {
        return enrollmentRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + id));
    }

    @Override
    public EnrollmentDTO save(EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = convertToEntity(enrollmentDTO);
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return convertToDTO(savedEnrollment);
    }

    @Override
    public EnrollmentDTO update(EnrollmentDTO enrollmentDTO) {
        if (!enrollmentRepository.existsById(enrollmentDTO.getId())) {
            throw new RuntimeException("Enrollment not found with id: " + enrollmentDTO.getId());
        }
        Enrollment enrollment = convertToEntity(enrollmentDTO);
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return convertToDTO(updatedEnrollment);
    }

    @Override
    public void deleteById(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new RuntimeException("Enrollment not found with id: " + id);
        }
        enrollmentRepository.deleteById(id);
    }

    private EnrollmentDTO convertToDTO(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(Long.valueOf(enrollment.getId()));
        dto.setStudentId(Long.valueOf(enrollment.getStudentId()));
        dto.setCourseId(Long.parseLong(enrollment.getCourseId()));
        dto.setSemesterId(Long.parseLong(enrollment.getSemesterId()));
        dto.setEnrollmentDate(enrollment.getEnrollmentDate().atStartOfDay());
        dto.setStatus(enrollment.getStatus());
        return dto;
    }

    private Enrollment convertToEntity(EnrollmentDTO dto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(dto.getId().intValue());
        enrollment.setStudentId(dto.getStudentId().intValue());
        enrollment.setCourseId(String.valueOf(dto.getCourseId()));
        enrollment.setSemesterId(String.valueOf(dto.getSemesterId()));
        enrollment.setEnrollmentDate(dto.getEnrollmentDate().toLocalDate());
        enrollment.setStatus(dto.getStatus());
        return enrollment;
    }
} 