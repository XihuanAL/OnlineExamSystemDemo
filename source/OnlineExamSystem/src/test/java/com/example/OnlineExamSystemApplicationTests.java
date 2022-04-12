package com.example;

import com.example.domain.Subject;
import com.example.mapper.SubjectMapper;
import com.example.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineExamSystemApplicationTests {

    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private SubjectService subjectService;

    @Test
    void testSubjectService() {
        subjectService.save(new Subject("高一", "数学"));
    }
    @Test
    void testSubjectMapper() {
        subjectMapper.deleteById(1);
        System.out.println(subjectMapper.insert(new Subject("高一", "语文")));
    }
    @Test
    void contextLoads() {
    }

}
