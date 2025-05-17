package com.haydarjohn.OBS.service;

import com.haydarjohn.OBS.dto.DepartmentDTO;
import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> findAll();
    DepartmentDTO findById(Long id);
    DepartmentDTO save(DepartmentDTO departmentDTO);
    DepartmentDTO update(DepartmentDTO departmentDTO);
    void deleteById(Long id);
} 