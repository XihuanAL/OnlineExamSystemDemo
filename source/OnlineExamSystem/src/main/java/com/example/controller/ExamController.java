package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Exam;
import com.example.domain.Question;
import com.example.domain.RestResponse;
import com.example.domain.Subject;
import com.example.service.ExamService;
import com.example.service.SubjectService;
import com.example.viewmodel.admin.ExamVM;
import com.example.viewmodel.admin.QuestionVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {
    private final ExamService examService;
    private final SubjectService subjectService;

    @Autowired
    public ExamController(ExamService examService, SubjectService subjectService) {
        this.examService = examService;
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RestResponse delete(@PathVariable("id") Integer id) {
        examService.removeById(id);
        return RestResponse.success();
    }

    @RequestMapping(value = "{currentPage}/{pageSize}", method = RequestMethod.GET)
    public RestResponse getQuestionPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        IPage<Exam> page = new Page<>(currentPage, pageSize);
        IPage<Exam> subjectIPage = examService.page(page);
        IPage<ExamVM> examVMIPage = subjectIPage.convert(Exam -> {
            ExamVM examVM = new ExamVM(Exam);
            examVM.setGradeName(subjectService.getById(Exam.getSubjectId()).getGradeName());
            examVM.setSubjectName(subjectService.getById(Exam.getSubjectId()).getSubjectName());
            return examVM;
        });
        return RestResponse.success(examVMIPage);
    }
}
