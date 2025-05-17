package com.haydarjohn.OBS.repository;

import com.haydarjohn.OBS.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Custom query methods can be added here
} 