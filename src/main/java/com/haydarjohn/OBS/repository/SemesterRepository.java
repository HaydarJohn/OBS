package com.haydarjohn.OBS.repository;

import com.haydarjohn.OBS.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, String> {
    // Custom query methods can be added here
} 