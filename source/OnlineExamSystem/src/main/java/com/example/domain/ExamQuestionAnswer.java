package com.example.domain;

import lombok.Data;

@Data
public class ExamQuestionAnswer {
    private Integer id;
    private Integer examAnswerId;
    private Integer questionId;
    private String questionAnswer;
    private Integer doRight;

    public ExamQuestionAnswer() {
    }
    public ExamQuestionAnswer(Integer examAnswerId, Integer questionId, String questionAnswer) {
        this.examAnswerId = examAnswerId;
        this.questionId = questionId;
        this.questionAnswer = questionAnswer;
    }
}
