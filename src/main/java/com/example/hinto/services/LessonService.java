package com.example.hinto.services;

import com.example.hinto.models.LessonDTO;

import java.util.List;

public interface LessonService {

    Long saveLesson(LessonDTO lessonDTO);

    List<LessonDTO> getAllLessons();

    LessonDTO findLessonById(Long id);

    LessonDTO updateLesson(LessonDTO lessonDTO, Long id);

    void deleteLesson(Long id);

    
}
