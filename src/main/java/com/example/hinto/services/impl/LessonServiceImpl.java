package com.example.hinto.services.impl;

import com.example.hinto.entities.Lesson;
import com.example.hinto.models.LessonDTO;
import com.example.hinto.repositories.LessonRepository;
import com.example.hinto.services.LessonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {

    // Constructor
    private LessonRepository lessonRepository;

    private ModelMapper modelMapper;

    public LessonServiceImpl(LessonRepository lessonRepository, ModelMapper modelMapper) {
        this.lessonRepository = lessonRepository;
        this.modelMapper = modelMapper;
    }

    // Methods
    @Override
    public Long saveLesson(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        modelMapper.map(lessonDTO,lesson);
        lesson.setDate1(LocalDateTime.now());
        lesson =  lessonRepository.save(lesson);
        return lesson.getId();
    }

    @Override
    public List<LessonDTO> getAllLessons() {
        List<Lesson> lessonList = lessonRepository.findAll();
        if(!CollectionUtils.isEmpty(lessonList)){
           return lessonList.stream().map(lesson -> modelMapper.map(lesson, LessonDTO.class )).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public LessonDTO findLessonById(Long id) {
        if(id != null){
           Lesson lesson =  lessonRepository.findLessonById(id);
           if(lesson != null){
               LessonDTO lessonDTO = new LessonDTO();
               modelMapper.map(lesson, lessonDTO);
               return lessonDTO;
           }
        }
        return null;
    }

    @Override
    public LessonDTO updateLesson(LessonDTO lessonDTO, Long id) {
        Lesson lesson = new Lesson();
        modelMapper.map(lessonDTO, lesson);
        lesson.setId(id);
        lesson.setDate1(LocalDateTime.now());
        lessonRepository.save(lesson);
        modelMapper.map(lesson, lessonDTO);
        return lessonDTO;
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }
}
