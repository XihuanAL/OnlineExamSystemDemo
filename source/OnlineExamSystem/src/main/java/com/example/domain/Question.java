package com.example.domain;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private Integer subject;
    private String  quesContent;
    private String ansContent;
    private String correct;
    private Integer score;
    private String type;

    public Question() {
    }
}
