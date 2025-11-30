package com.prabhat.QuizApp.service;


import com.prabhat.QuizApp.entity.Question;
import com.prabhat.QuizApp.entity.QuestionDTO;
import com.prabhat.QuizApp.entity.Quiz;
import com.prabhat.QuizApp.entity.Response;
import com.prabhat.QuizApp.repository.QuestionRepository;
import com.prabhat.QuizApp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    public ResponseEntity<String> createQuiz(String category, String title, int numQ) {

        List<Question> questions = quizRepository.findRandomQuestionByCategory(category, numQ);

        if (questions.isEmpty()) {
            return new ResponseEntity<>("No questions found for category: " + category,
                    HttpStatus.NOT_FOUND);
        }

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizRepository.save(quiz);

        return new ResponseEntity<>("Quiz Created Successfully", HttpStatus.CREATED);
    }

    public List<Object[]> getQuizById(int id) {
        return quizRepository.getQuizById(id);
    }

    public ResponseEntity<Integer> calcResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();

        List<Question> questions = quiz.getQuestions();
        int right=0;
        int i=0;
        for(Response response1 :responses){
            if(response1.getResponse().equals(questions.get(i))){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
