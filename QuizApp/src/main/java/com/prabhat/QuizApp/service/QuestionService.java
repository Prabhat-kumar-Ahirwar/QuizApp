package com.prabhat.QuizApp.service;

import com.prabhat.QuizApp.entity.Question;
import com.prabhat.QuizApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<?> getAllQuestions() {
        try {
            List<Question> questions = questionRepository.findAll();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching questions: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getQuestionByCategory(String category) {
        try {
            List<Question> questions = questionRepository.findByCategory(category);
            if (questions.isEmpty()) {
                return new ResponseEntity<>("No questions found for category: " + category,
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching category: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findBydifficultylevel(String difficultylevel) {
        try {
            List<Question> questions = questionRepository.findBydifficultylevel(difficultylevel);
            if (questions.isEmpty()) {
                return new ResponseEntity<>("No questions found for difficulty: " + difficultylevel,
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching difficulty: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> addQuestion(Question question) {
        try {
            Question saved = questionRepository.save(question);
            return new ResponseEntity<>("Question Saved Successfully: ID = " + saved.getId(),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving question: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ----------------------------------------
    // 🔥 Update Question By ID
    // ----------------------------------------
    public ResponseEntity<?> updateQuestion(Integer id, Question updatedData) {
        try {
            return questionRepository.findById(id).map(existingQuestion -> {

                existingQuestion.setCategory(updatedData.getCategory());
                existingQuestion.setDifficultylevel(updatedData.getDifficultylevel());
                existingQuestion.setOption1(updatedData.getOption1());
                existingQuestion.setOption2(updatedData.getOption2());
                existingQuestion.setOption3(updatedData.getOption3());
                existingQuestion.setOption4(updatedData.getOption4());
                existingQuestion.setQuestionTitle(updatedData.getQuestionTitle());
                existingQuestion.setRightAnswer(updatedData.getRightAnswer());

                questionRepository.save(existingQuestion);

                return new ResponseEntity<>("Question updated successfully (ID: " + id + ")",
                        HttpStatus.OK);

            }).orElseGet(() ->
                    new ResponseEntity<>("Question not found with ID: " + id,
                            HttpStatus.NOT_FOUND)
            );

        } catch (Exception e) {
            return new ResponseEntity<>("Error updating question: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteQuestionById(Integer id) {
        try {
            if (!questionRepository.existsById(id)) {
                return new ResponseEntity<>("Question not found with ID: " + id,
                        HttpStatus.NOT_FOUND);
            }

            questionRepository.deleteById(id);
            return new ResponseEntity<>("Question deleted successfully (ID: " + id + ")",
                    HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting question: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
