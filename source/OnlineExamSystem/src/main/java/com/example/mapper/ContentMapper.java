package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Content;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentMapper extends BaseMapper<Content> {
}
