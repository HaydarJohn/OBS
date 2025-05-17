package com.haydarjohn.OBS.repository;

import com.haydarjohn.OBS.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    // Custom query methods can be added here
} 