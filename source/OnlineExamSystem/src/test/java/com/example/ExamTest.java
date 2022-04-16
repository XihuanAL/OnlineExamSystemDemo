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

    @Test
    public void testExamAdd()  {
        List<Integer> list = new ArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(new Exam("examName", list, 100, 1));
        //examMapper.insert(new Exam("examName", list, 100, 1));
    }
    @Test
    public void test() throws JsonProcessingException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("list1:" + list);
//        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        System.out.println("json:" + json);
        ArrayList<Integer> list2 = mapper.readValue(json, ArrayList.class);
        for (Integer integer : list2) {
            System.out.println(integer);

        }
        //examService.save(new Exam(json));
        //System.out.println(json);
    }
}
