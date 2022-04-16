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
    private String correct;
    private Integer score;
    private String type;

    public QuestionVM() {
    }

    public QuestionVM(String gradeName, String subjectName, String quesContent, String correct, Integer score, String type) {
        this.gradeName = gradeName;
        this.subjectName = subjectName;
        this.quesContent = quesContent;
        this.correct = correct;
        this.score = score;
        this.type = type;
    }

    public QuestionVM(Subject subject, Question question) {
        this.id=question.getId();
        this.gradeName=subject.getGradeName();
        this.subjectName =subject.getSubjectName();
        this.correct=question.getCorrect();
        this.quesContent=question.getQuesContent();
        this.ansContent=question.getAnsContent();
        this.score=question.getScore();
        this.type=question.getType();
    }

    public QuestionVM(Question question) {
        this.id=question.getId();
        this.correct=question.getCorrect();
        this.quesContent=question.getQuesContent();
        this.ansContent=question.getAnsContent();
        this.score=question.getScore();
        this.type=question.getType();
    }
}
