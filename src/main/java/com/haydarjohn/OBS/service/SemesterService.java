package com.haydarjohn.OBS.service;

import com.haydarjohn.OBS.dto.SemesterDTO;
import java.util.List;

public interface SemesterService {
    List<SemesterDTO> findAll();
    SemesterDTO findById(Long id);
    SemesterDTO save(SemesterDTO semesterDTO);
    SemesterDTO update(SemesterDTO semesterDTO);
    void deleteById(Long id);
} 