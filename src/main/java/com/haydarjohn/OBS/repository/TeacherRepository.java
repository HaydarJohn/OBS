package com.haydarjohn.OBS.repository;

import com.haydarjohn.OBS.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    // Custom query methods can be added here
} 