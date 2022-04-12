package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.Content;
import com.example.mapper.ContentMapper;
import com.example.service.ContentService;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {
}
