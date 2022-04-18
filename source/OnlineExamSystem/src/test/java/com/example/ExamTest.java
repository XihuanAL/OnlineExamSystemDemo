package com.example;


import com.example.domain.Exam;
import com.example.mapper.ExamMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ExamTest {

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private ObjectMapper mapper;


}
