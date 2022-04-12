package com.example.domain;

import lombok.Data;

@Data
public class Subject {
    private Integer id;
    private String gradeName;
    private String subjectName;

    public Subject() {
    }

    public Subject(String gradeName, String subjectName) {
        this.gradeName = gradeName;
        this.subjectName = subjectName;
    }

    public Subject(String gradeName) {
        this.gradeName = gradeName;
    }
}
