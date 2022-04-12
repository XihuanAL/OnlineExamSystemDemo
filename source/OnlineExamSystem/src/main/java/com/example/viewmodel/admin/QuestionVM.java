package com.example.viewmodel.admin;

import com.example.domain.Question;
import com.example.domain.Subject;
import lombok.Data;

@Data
public class QuestionVM {
    private Integer id;
    private String gradeName;
    private String subjectName;
    private String quesContent;
    private String ansContent;
    private Integer score;
    private String Type;

    public QuestionVM(Subject subject, Question question) {
        this.id=question.getId();
        this.gradeName=subject.getGradeName();
        this.subjectName =subject.getSubjectName();
        this.quesContent=question.getQuesContent();
        this.ansContent=question.getAnsContent();
        this.score=question.getScore();
        this.Type=question.getType();
    }
}
