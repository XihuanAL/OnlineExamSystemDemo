package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Question;
import com.example.domain.RestResponse;
import com.example.domain.Subject;
import com.example.service.QuestionService;
import com.example.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;
    private final QuestionService questionService;
    private final Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    public SubjectController(SubjectService subjectService, QuestionService questionService) {
        this.subjectService = subjectService;
        this.questionService = questionService;
    }

    @RequestMapping(value = "/grade",method = RequestMethod.GET)
    public RestResponse<List<Subject>> getGrades() {
        List<Subject> subjects = subjectService.list();
        HashSet<String> gradeNames = new HashSet<>();
        subjects.forEach(subject -> {
            gradeNames.add(subject.getGradeName());
        });
        List<Subject> ret = new ArrayList<>();
        gradeNames.forEach(gradeName -> {
           ret.add(new Subject(gradeName));
        });
        return RestResponse.success(ret);
    }

    @RequestMapping(method = RequestMethod.GET)
    public RestResponse<List<Subject>> getSubjects(String gradeName) {
        LambdaQueryWrapper<Subject> queryWrapper = new LambdaQueryWrapper<>();
        if(gradeName == "") gradeName = null;
        queryWrapper.eq(gradeName != null, Subject::getGradeName , gradeName);
        List<Subject> subjects = subjectService.list(queryWrapper);
        return RestResponse.success(subjects);
    }

    @RequestMapping(method = RequestMethod.POST)
    public RestResponse getSubject(@RequestBody Subject subject) {
        subjectService.save(subject);
        return RestResponse.success();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public RestResponse deleteSubject(@PathVariable Integer id) {
        subjectService.removeById(id);
        questionService.remove(new LambdaQueryWrapper<Question>().eq(Question::getSubjectId, id));
        return RestResponse.success();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public RestResponse updateSubject(@RequestBody Subject subject) {
        subjectService.updateById(subject);
        return RestResponse.success();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public RestResponse<Subject> getSubject(@PathVariable Integer id) {
        Subject subject = subjectService.getById(id);
        return RestResponse.success(subject);
    }

    @RequestMapping(value = "{currentPage}/{pageSize}", method = RequestMethod.GET)
    public RestResponse<List<Subject>> getSubjects(@PathVariable Integer currentPage,@PathVariable Integer pageSize ,Subject subject) {
        LambdaQueryWrapper<Subject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(subject.getSubjectName() != null, Subject::getSubjectName, subject.getSubjectName());
        queryWrapper.like(subject.getGradeName() != null, Subject::getGradeName , subject.getGradeName());
        IPage<Subject> page = new Page<>(currentPage, pageSize);
        IPage<Subject> subjectIPage = subjectService.page(page, queryWrapper);
        return RestResponse.success(subjectIPage);
    }
}
