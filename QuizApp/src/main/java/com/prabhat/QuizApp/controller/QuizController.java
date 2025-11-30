package com.prabhat.QuizApp.controller;

import com.prabhat.QuizApp.entity.Response;
import com.prabhat.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

        @PostMapping("/create")
        public ResponseEntity<String> createQuiz(@RequestParam String category ,@RequestParam String title , @RequestParam int numQ  ){
            return quizService.createQuiz(category,title,numQ);
        }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<Object[]>> getQuizById(@PathVariable int id) {
        List<Object[]> questions = quizService.getQuizById(id);
        if (questions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitResponse(@PathVariable Integer id , @RequestBody List<Response> response){
            return  quizService.calcResult(id,response);
    }
}
