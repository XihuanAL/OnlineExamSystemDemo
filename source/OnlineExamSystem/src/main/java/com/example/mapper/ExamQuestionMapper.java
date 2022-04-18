package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.ExamQuestion;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamQuestionMapper extends BaseMapper<ExamQuestion> {
}
