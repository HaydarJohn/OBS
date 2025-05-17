package com.haydarjohn.OBS.service.impl;

import com.haydarjohn.OBS.dto.DepartmentDTO;
import com.haydarjohn.OBS.entity.Department;
import com.haydarjohn.OBS.repository.DepartmentRepository;
import com.haydarjohn.OBS.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO findById(Long id) {
        return departmentRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }

    @Override
    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        Department department = convertToEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return convertToDTO(savedDepartment);
    }

    @Override
    public DepartmentDTO update(DepartmentDTO departmentDTO) {
        if (!departmentRepository.existsById(departmentDTO.getId())) {
            throw new RuntimeException("Department not found with id: " + departmentDTO.getId());
        }
        Department department = convertToEntity(departmentDTO);
        Department updatedDepartment = departmentRepository.save(department);
        return convertToDTO(updatedDepartment);
    }

    @Override
    public void deleteById(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }

    private DepartmentDTO convertToDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setDepName(department.getDepName());
        dto.setHeadOfDepartment(department.getHeadOfDepartment());
        dto.setBudget(department.getBudget());
        return dto;
    }

    private Department convertToEntity(DepartmentDTO dto) {
        Department department = new Department();
        department.setId(dto.getId());
        department.setDepName(dto.getDepName());
        department.setHeadOfDepartment(dto.getHeadOfDepartment());
        department.setBudget(dto.getBudget());
        return department;
    }
} 