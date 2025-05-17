package com.haydarjohn.OBS.service.impl;

import com.haydarjohn.OBS.dto.AdministratorDTO;
import com.haydarjohn.OBS.entity.Administrator;
import com.haydarjohn.OBS.repository.AdministratorRepository;
import com.haydarjohn.OBS.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public List<AdministratorDTO> findAll() {
        return administratorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdministratorDTO findById(Long id) {
        return administratorRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Administrator not found with id: " + id));
    }

    @Override
    public AdministratorDTO save(AdministratorDTO administratorDTO) {
        Administrator administrator = convertToEntity(administratorDTO);
        Administrator savedAdministrator = administratorRepository.save(administrator);
        return convertToDTO(savedAdministrator);
    }

    @Override
    public AdministratorDTO update(AdministratorDTO administratorDTO) {
        if (!administratorRepository.existsById(administratorDTO.getId())) {
            throw new RuntimeException("Administrator not found with id: " + administratorDTO.getId());
        }
        Administrator administrator = convertToEntity(administratorDTO);
        Administrator updatedAdministrator = administratorRepository.save(administrator);
        return convertToDTO(updatedAdministrator);
    }

    @Override
    public void deleteById(Long id) {
        if (!administratorRepository.existsById(id)) {
            throw new RuntimeException("Administrator not found with id: " + id);
        }
        administratorRepository.deleteById(id);
    }

    private AdministratorDTO convertToDTO(Administrator administrator) {
        AdministratorDTO dto = new AdministratorDTO();
        dto.setId(administrator.getId());
        dto.setFirstName(administrator.getFirstName());
        dto.setLastName(administrator.getLastName());
        dto.setEmail(administrator.getEmail());
        return dto;
    }

    private Administrator convertToEntity(AdministratorDTO dto) {
        Administrator administrator = new Administrator();
        administrator.setId(dto.getId());
        administrator.setFirstName(dto.getFirstName());
        administrator.setLastName(dto.getLastName());
        administrator.setEmail(dto.getEmail());
        return administrator;
    }
} 