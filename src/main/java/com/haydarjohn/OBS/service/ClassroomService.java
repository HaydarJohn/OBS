package com.haydarjohn.OBS.service;

import com.haydarjohn.OBS.dto.ClassroomDTO;
import java.util.List;

public interface ClassroomService {
    List<ClassroomDTO> findAll();
    ClassroomDTO findById(String id);
    ClassroomDTO save(ClassroomDTO classroomDTO);
    ClassroomDTO update(ClassroomDTO classroomDTO);
    void deleteById(String id);
} 