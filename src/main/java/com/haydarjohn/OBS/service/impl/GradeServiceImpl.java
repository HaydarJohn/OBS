package com.haydarjohn.OBS.service.impl;

import com.haydarjohn.OBS.dto.GradeDTO;
import com.haydarjohn.OBS.entity.Grade;
import com.haydarjohn.OBS.repository.GradeRepository;
import com.haydarjohn.OBS.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<GradeDTO> findAll() {
        return gradeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GradeDTO findById(Long id) {
        return gradeRepository.findById(String.valueOf(id))
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Grade not found with id: " + id));
    }

    @Override
    public GradeDTO save(GradeDTO gradeDTO) {
        Grade grade = convertToEntity(gradeDTO);
        Grade savedGrade = gradeRepository.save(grade);
        return convertToDTO(savedGrade);
    }

    @Override
    public GradeDTO update(GradeDTO gradeDTO) {
        if (!gradeRepository.existsById(String.valueOf(gradeDTO.getId()))) {
            throw new RuntimeException("Grade not found with id: " + gradeDTO.getId());
        }
        Grade grade = convertToEntity(gradeDTO);
        Grade updatedGrade = gradeRepository.save(grade);
        return convertToDTO(updatedGrade);
    }

    @Override
    public void deleteById(Long id) {
        if (!gradeRepository.existsById(String.valueOf(id))) {
            throw new RuntimeException("Grade not found with id: " + id);
        }
        gradeRepository.deleteById(String.valueOf(id));
    }

    private GradeDTO convertToDTO(Grade grade) {
        GradeDTO dto = new GradeDTO();
        dto.setId(Long.parseLong(grade.getGradeId()));
        dto.setStudentId(Long.valueOf(grade.getEnrollmentId()));
        dto.setLetterGrade(grade.getTotalGrade());
        return dto;
    }

    private Grade convertToEntity(GradeDTO dto) {
        Grade grade = new Grade();
        grade.setGradeId(String.valueOf(dto.getId()));
        grade.setEnrollmentId(dto.getStudentId().intValue());
        grade.setTotalGrade(dto.getLetterGrade());
        return grade;
    }
} 