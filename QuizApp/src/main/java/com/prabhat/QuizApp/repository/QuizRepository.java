package com.prabhat.QuizApp.repository;

import com.prabhat.QuizApp.entity.Question;
import com.prabhat.QuizApp.entity.QuestionDTO;
import com.prabhat.QuizApp.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz , Integer> {

    @Query(
            value = "SELECT * FROM questions q WHERE q.category = :category ORDER BY RAND() LIMIT :limit",
            nativeQuery = true
    )
    List<Question> findRandomQuestionByCategory(
            @Param("category") String category,
            @Param("limit") int limit
    );
    @Query(
            value = "SELECT q.id, q.category, q.difficultylevel, q.option1, q.option2, q.option3, q.option4, q.question_title " +
                    "FROM questions q JOIN quiz_questions qq ON q.id = qq.question_id " +
                    "WHERE qq.quiz_id = :id",
            nativeQuery = true
    )
    List<Object[]> getQuizById(@Param("id") int id);

}
