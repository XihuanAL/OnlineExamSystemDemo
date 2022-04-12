package com.example.domain;

import lombok.Data;

@Data
public class Content {
    private Integer id;
    private String content;

    public Content(String content) {
        this.content = content;
    }
    public Content(Integer id, String content) {
        this.id = id;
        this.content = content;
    }
}
