package com.example.hinto.controllers;

import com.example.hinto.exception.NotFoundException;
import com.example.hinto.models.LessonDTO;
import com.example.hinto.services.LessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/data")
public class LessonController {

    // Logger
    private Logger logger = LoggerFactory.getLogger(LessonController.class);

    private static String NOT_FOUND = "Id not found!";

    // Constructor Injection
    private LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    /**
     * [Post] Inserts a new row(lesson) into the database based on the request body.
     * [Path]  http://localhost:8080/v1/data
     * @return
     */
    @PostMapping
    public Long addLesson(@RequestBody LessonDTO lessonDTO){
        String message = "Lesson successfully added.";
        logger.info(message);
        return lessonService.saveLesson(lessonDTO);
    }

    /**
     * [Get] Retrieves a single row from the database based on the provided id parameter.
     * [Path]  http://localhost:8080/v1/data/{id}
     * @return
     */
    @GetMapping("/{id}")
    public LessonDTO getLesson(@PathVariable("id")Long id){
        LessonDTO lessonDTO = lessonService.findLessonById(id);
        if(lessonDTO == null){
            logger.error("Not found!");
            throw new NotFoundException(NOT_FOUND);
        }
        return lessonDTO;
    }

    /**
     * [Put]  Updates an existing row(lesson) in the database based on the provided id path parameter and request body.
     * [Path]  http://localhost:8080/v1/data/{id}
     * @return
     */

    @PutMapping("/{id}")
    public LessonDTO updateLesson(@PathVariable("id")Long id, @RequestBody LessonDTO lessonDTO){
        LessonDTO lesson = lessonService.findLessonById(id);
        String warning = "Lesson does not exist.";
        if(lesson == null){
            logger.warn(warning);
            throw new NotFoundException(NOT_FOUND);
        }
        return lessonService.updateLesson(lessonDTO, id);
    }

    /**
     * [Delete]  Deletes a single row(lesson) from the database based on the provided id parameter.
     * [Path]  http://localhost:8080/v1/data/{id}
     */

    @DeleteMapping("/{id}")
    public String deleteLesson(@PathVariable("id")Long id){
        LessonDTO lesson = lessonService.findLessonById(id);
        if(lesson == null){
            return NOT_FOUND;
        }
        lessonService.deleteLesson(id);
        return "Lesson deleted successfully.";
    }


    /**
     * The SQL script to create the table:
     CREATE TABLE `lessons` (
     `id` bigint NOT NULL AUTO_INCREMENT,
     `date1` datetime(6) DEFAULT NULL,
     `qty` int DEFAULT NULL,
     `title` varchar(255) DEFAULT NULL,
     PRIMARY KEY (`id`)
     )
     */



















}
