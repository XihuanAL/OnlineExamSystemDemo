package com.example.domain;

import com.example.viewmodel.admin.QuestionVM;
import lombok.Data;

@Data
public class Question {
    private Integer id;
    private Integer subjectId;
    private String  quesContent;
    private String ansContent;
    private String correct;
    private Integer score;
    private String type;

    public Question() {
    }

    public Question(QuestionVM vm) {
        this.quesContent = vm.getQuesContent();
        this.ansContent = vm.getAnsContent();
        this.correct = vm.getCorrect();
        this.score = vm.getScore();
        this.type = vm.getType();
    }
}
