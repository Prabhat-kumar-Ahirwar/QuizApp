package com.prabhat.QuizApp.repository;

import com.prabhat.QuizApp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    List<Question> findBydifficultylevel(String difficultylevel);
}
