package com.haydarjohn.OBS.repository;

import com.haydarjohn.OBS.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, String> {
    // Custom query methods can be added here
} 