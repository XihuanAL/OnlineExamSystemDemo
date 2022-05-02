package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.ExamQuestionAnswer;
import com.example.mapper.ExamQuestionAnswerMapper;
import com.example.service.ExamQuestionAnswerService;
import org.springframework.stereotype.Service;

@Service
public class ExamQuestionAnswerServiceImpl extends ServiceImpl<ExamQuestionAnswerMapper, ExamQuestionAnswer> implements ExamQuestionAnswerService {
}
