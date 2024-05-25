package es.carlos3.rocamora.hernandez.pfcbackend.controller;

import es.carlos3.rocamora.hernandez.pfcbackend.model.Booking;
import es.carlos3.rocamora.hernandez.pfcbackend.model.Lesson;
import es.carlos3.rocamora.hernandez.pfcbackend.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class LessonController {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonController(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @GetMapping("/lessons")
    List<String> findLessons(){
        try {
            return (List<String>) lessonRepository.findDistinctSubject();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }
}
