package com.prabhat.QuizApp.controller;

import com.prabhat.QuizApp.entity.Question;
import com.prabhat.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // -------------------------
    // Get All Questions
    // -------------------------
    @GetMapping("/all")
    public ResponseEntity<?> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // -------------------------
    // Get by Category
    // -------------------------
    @GetMapping("/category/{category}")
    public ResponseEntity<?> getQuestionsByCategory(@PathVariable("category") String category) {
        return questionService.getQuestionByCategory(category);
    }

    // -------------------------
    // Get by Difficulty Level
    // -------------------------
    @GetMapping("/difficulty/{level}")
    public ResponseEntity<?> getQuestionsByDifficulty(@PathVariable("level") String difficultylevel) {
        return questionService.findBydifficultylevel(difficultylevel);
    }

    // -------------------------
    // Add a Question
    // -------------------------
    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    // -------------------------
    // Update Question by ID
    // -------------------------
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateQuestion(
            @PathVariable Integer id,
            @RequestBody Question updatedQuestion) {

        return questionService.updateQuestion(id, updatedQuestion);
    }

    // -------------------------
    // Delete Question by ID
    // -------------------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestionById(id);
    }
}
