package com.example;

import com.example.domain.Content;
import com.example.mapper.ContentMapper;
import com.example.service.ContentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContentTest {
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ContentService contentService;


    @Test
    public void testContentService() {
        System.out.println(contentService.save(new Content("空")));
    }

    @Test
    public void testContentMapper() {
        System.out.println(contentMapper.insert(new Content(1,"test")));
    }
}
