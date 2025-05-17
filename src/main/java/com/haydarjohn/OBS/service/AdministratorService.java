package com.haydarjohn.OBS.service;

import com.haydarjohn.OBS.dto.AdministratorDTO;
import java.util.List;

public interface AdministratorService {
    List<AdministratorDTO> findAll();
    AdministratorDTO findById(Long id);
    AdministratorDTO save(AdministratorDTO administratorDTO);
    AdministratorDTO update(AdministratorDTO administratorDTO);
    void deleteById(Long id);
} 