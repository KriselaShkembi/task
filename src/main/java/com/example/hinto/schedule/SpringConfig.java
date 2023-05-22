package com.example.hinto.schedule;

import com.example.hinto.models.LessonDTO;
import com.example.hinto.services.LessonService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class SpringConfig {
    LessonService lessonService;

    public SpringConfig(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Scheduled(cron = "0 5 * * * ?")
    public void scheduleFixedDelayTask() {
      List<LessonDTO> lessonDTOS = lessonService.getAllLessons();

      lessonDTOS.forEach(lessonDTO -> lessonDTO.setQty(lessonDTO.getQty()+1));
      lessonDTOS.forEach(lessonDTO -> lessonService.saveLesson(lessonDTO));

    }
}