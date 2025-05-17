package com.haydarjohn.OBS.repository;

import com.haydarjohn.OBS.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, String> {
    // Custom query methods can be added here
} 