package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.ExamQuestion;
import com.example.mapper.ExamQuestionMapper;
import com.example.service.ExamQuestionService;
import org.springframework.stereotype.Service;

@Service
public class ExamQuestionServiceImpl extends ServiceImpl<ExamQuestionMapper, ExamQuestion> implements ExamQuestionService {
}
