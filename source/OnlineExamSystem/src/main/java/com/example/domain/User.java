package com.example.domain;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String realName;
    private String role;
}
