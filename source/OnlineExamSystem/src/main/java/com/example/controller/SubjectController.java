package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.RestResponse;
import com.example.domain.Subject;
import com.example.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public RestResponse getSubject(@RequestBody Subject subject) {
        subjectService.save(subject);
        return RestResponse.success();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public RestResponse deleteSubject(@PathVariable Integer id) {
        subjectService.removeById(id);
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
        queryWrapper.like(subject.getSubject() != null, Subject::getSubject , subject.getSubject());
        queryWrapper.like(subject.getGrade() != null, Subject::getGrade , subject.getGrade());
        IPage<Subject> page = new Page<>(currentPage, pageSize);
        IPage<Subject> subjectIPage = subjectService.page(page, queryWrapper);
        return RestResponse.success(subjectIPage);
    }
}
