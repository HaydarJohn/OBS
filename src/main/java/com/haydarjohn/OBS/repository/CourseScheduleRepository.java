package com.haydarjohn.OBS.repository;

import com.haydarjohn.OBS.entity.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseScheduleRepository extends JpaRepository<CourseSchedule, Long> {
    // Custom query methods can be added here
} 