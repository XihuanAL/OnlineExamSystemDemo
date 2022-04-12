package com.example.domain;

import lombok.Data;

@Data
public class Subject {
    private Integer id;
    private String grade;
    private String subject;

    public Subject(String grade, String subject) {
        this.grade = grade;
        this.subject = subject;
    }
}
