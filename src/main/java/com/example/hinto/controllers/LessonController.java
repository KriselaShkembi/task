package com.example.hinto.controllers;

import com.example.hinto.entities.Lesson;
import com.example.hinto.exception.NotFoundException;
import com.example.hinto.models.LessonDTO;
import com.example.hinto.services.LessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("v1/data")
public class MainController {

    // Logger
    private Logger logger = LoggerFactory.getLogger(MainController.class);

    private static String NOT_FOUND = "Id not foubd";

    // Constructor Injection
    private LessonService lessonService;

    public MainController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    /**
     * [Post] Inserts a new row(lesson) into the database based on the request body.
     * [Path]  http://localhost:8080/v1/data/lesson
     * @return
     */
    @PostMapping
    public Long addLesson(@RequestBody LessonDTO lessonDTO){
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
    public LessonDTO updateLesson(@PathVariable("id")Long id, LessonDTO lessonDTO){
        LessonDTO lesson = lessonService.findLessonById(id);
        if(lesson == null){
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
        return "Delete Sucesfully";
    }


    /**
     * The SQL script to create the table.
     CREATE TABLE lessons
     (
     id long  PRIMARY KEY,
     title varchar(255),
     qty int,
     date date1
     );
     */



















}
