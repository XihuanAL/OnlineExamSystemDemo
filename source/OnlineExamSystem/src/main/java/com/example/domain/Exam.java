package com.example.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Exam {
    private Integer id;
    private String examName;
    private List<Integer> questionIdList;
    private Integer totalScore;
    private Integer subjectId;

    public Exam() {

    }

    public Exam(String examName, List<Integer> questionIdList, Integer totalScore, Integer subjectId) {
        this.examName = examName;
        this.questionIdList = questionIdList;
        this.totalScore = totalScore;
        this.subjectId = subjectId;
    }

    public Exam(List<Integer> questionIdList) {
        this.questionIdList = questionIdList;
    }
}
