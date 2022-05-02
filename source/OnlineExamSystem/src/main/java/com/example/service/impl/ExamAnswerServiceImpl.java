package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.ExamAnswer;
import com.example.mapper.ExamAnswerMapper;
import com.example.service.ExamAnswerService;
import org.springframework.stereotype.Service;

@Service
public class ExamAnswerServiceImpl extends ServiceImpl<ExamAnswerMapper, ExamAnswer> implements ExamAnswerService {
}
