package com.example.domain;

import lombok.Data;

@Data
public class ExamQuestion {
    private Integer id;
    private Integer examId;
    private Integer questionId;

    public ExamQuestion() {
    }

    public ExamQuestion(Integer examId, Integer questionId) {
        this.examId = examId;
        this.questionId = questionId;
    }
}
