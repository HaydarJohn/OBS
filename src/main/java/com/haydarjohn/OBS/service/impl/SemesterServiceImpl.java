package com.haydarjohn.OBS.service.impl;

import com.haydarjohn.OBS.dto.SemesterDTO;
import com.haydarjohn.OBS.entity.Semester;
import com.haydarjohn.OBS.repository.SemesterRepository;
import com.haydarjohn.OBS.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SemesterServiceImpl implements SemesterService {
    
    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterServiceImpl(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @Override
    public List<SemesterDTO> findAll() {
        return semesterRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SemesterDTO findById(Long id) {
        return semesterRepository.findById(String.valueOf(id))
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Semester not found with id: " + id));
    }

    @Override
    public SemesterDTO save(SemesterDTO semesterDTO) {
        Semester semester = convertToEntity(semesterDTO);
        Semester savedSemester = semesterRepository.save(semester);
        return convertToDTO(savedSemester);
    }

    @Override
    public SemesterDTO update(SemesterDTO semesterDTO) {
        if (!semesterRepository.existsById(String.valueOf(semesterDTO.getId()))) {
            throw new RuntimeException("Semester not found with id: " + semesterDTO.getId());
        }
        Semester semester = convertToEntity(semesterDTO);
        Semester updatedSemester = semesterRepository.save(semester);
        return convertToDTO(updatedSemester);
    }

    @Override
    public void deleteById(Long id) {
        if (!semesterRepository.existsById(String.valueOf(id))) {
            throw new RuntimeException("Semester not found with id: " + id);
        }
        semesterRepository.deleteById(String.valueOf(id));
    }

    private SemesterDTO convertToDTO(Semester semester) {
        SemesterDTO dto = new SemesterDTO();
        dto.setId(Long.parseLong(semester.getSemesterId()));
        dto.setName(semester.getSemesterName());
        dto.setStartDate(semester.getStartDate());
        dto.setEndDate(semester.getEndDate());
        return dto;
    }

    private Semester convertToEntity(SemesterDTO dto) {
        Semester semester = new Semester();
        semester.setSemesterId(String.valueOf(dto.getId()));
        semester.setSemesterName(dto.getName());
        semester.setStartDate(dto.getStartDate());
        semester.setEndDate(dto.getEndDate());
        return semester;
    }
} 