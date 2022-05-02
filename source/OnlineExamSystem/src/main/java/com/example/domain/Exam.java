package com.example.domain;

import com.example.viewmodel.admin.ExamVM;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Exam {
    private Integer id;
    private String examName;
    private Integer subjectId;
    private Integer paperScore;

    public Exam() {
    }

    public Exam(String examName, Integer subjectId) {
        this.examName = examName;
        this.subjectId = subjectId;
    }

    public Exam(String examName, Integer subjectId, int paperScore) {
        this.examName = examName;
        this.subjectId = subjectId;
        this.paperScore = paperScore;
    }
}
