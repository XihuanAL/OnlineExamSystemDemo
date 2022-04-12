package com.example.viewmodel.admin;

import com.example.domain.Question;
import com.example.domain.Subject;
import lombok.Data;

@Data
public class QuestionVM {
    private Integer id;
    private String grade;
    private String subject;
    private String quesContent;
    private String ansContent;
    private Integer score;
    private String Type;

    public QuestionVM(Subject subject, Question question) {
        this.id=question.getId();
        this.grade=subject.getGrade();
        this.subject=subject.getSubject();
        this.quesContent=question.getQuesContent();
        this.ansContent=question.getAnsContent();
        this.score=question.getScore();
        this.Type=question.getType();
    }
}
