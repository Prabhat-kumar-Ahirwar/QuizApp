package com.prabhat.QuizApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String category;

    private String difficultylevel;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    @Column(name = "question_title", columnDefinition = "TEXT")
    private String questionTitle;

    @Column(name = "right_answer")
    private String rightAnswer;

}
