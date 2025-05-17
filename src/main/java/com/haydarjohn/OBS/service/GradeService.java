package com.haydarjohn.OBS.service;

import com.haydarjohn.OBS.dto.GradeDTO;
import java.util.List;

public interface GradeService {
    List<GradeDTO> findAll();
    GradeDTO findById(Long id);
    GradeDTO save(GradeDTO gradeDTO);
    GradeDTO update(GradeDTO gradeDTO);
    void deleteById(Long id);
} 