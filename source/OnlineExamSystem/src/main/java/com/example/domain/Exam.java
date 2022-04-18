package com.example.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Exam {
    private Integer id;
    private String examName;
    private Integer subjectId;

    public Exam() {

    }

    public Exam(String examName, Integer subjectId) {
        this.examName = examName;
        this.subjectId = subjectId;
    }

}
