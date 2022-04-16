package com.example.viewmodel.admin;

import lombok.Data;

@Data
public class ExamVM {
    private Integer id;
    private String examName;
    private String questionIdList;
    private Integer totalScore;
    private Integer subjectId;

    public ExamVM() {
    }

    public ExamVM(String questionIdList) {
        this.questionIdList = questionIdList;
    }
}
