package com.haydarjohn.OBS.controller;

import com.haydarjohn.OBS.dto.AdministratorDTO;
import com.haydarjohn.OBS.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrators")
public class AdministratorController {

    private final AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping
    public ResponseEntity<List<AdministratorDTO>> getAllAdministrators() {
        return ResponseEntity.ok(administratorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministratorDTO> getAdministratorById(@PathVariable Long id) {
        return ResponseEntity.ok(administratorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AdministratorDTO> createAdministrator(@RequestBody AdministratorDTO administratorDTO) {
        return ResponseEntity.ok(administratorService.save(administratorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministratorDTO> updateAdministrator(@PathVariable Long id, @RequestBody AdministratorDTO administratorDTO) {
        administratorDTO.setId(id);
        return ResponseEntity.ok(administratorService.update(administratorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrator(@PathVariable Long id) {
        administratorService.deleteById(id);
        return ResponseEntity.ok().build();
    }
} 