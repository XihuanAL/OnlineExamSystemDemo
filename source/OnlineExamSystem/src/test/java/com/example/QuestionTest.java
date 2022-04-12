package com.example;

import com.example.domain.Question;
import com.example.mapper.QuestionMapper;
import com.example.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionTest {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private SubjectService subjectService;

    @Test
    public void testQuestionMapper() {

    }
}
