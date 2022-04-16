package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Subject;

import java.util.List;

public interface SubjectService extends IService<Subject> {
    List<Subject> getByNames(String gradeName, String subjectName);

}
