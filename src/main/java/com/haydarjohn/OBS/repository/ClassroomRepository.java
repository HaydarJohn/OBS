package com.haydarjohn.OBS.repository;

import com.haydarjohn.OBS.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, String> {
    // Custom query methods can be added here
} 