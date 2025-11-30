package com.prabhat.QuizApp.entity;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
    private Integer id;
    private String response;
}
