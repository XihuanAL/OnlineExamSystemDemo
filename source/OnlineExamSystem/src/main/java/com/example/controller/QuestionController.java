package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Question;
import com.example.domain.RestResponse;
import com.example.domain.Subject;
import com.example.service.QuestionService;
import com.example.service.SubjectService;
import com.example.viewmodel.admin.QuestionVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final SubjectService subjectService;

    @Autowired
    public QuestionController(QuestionService questionService, SubjectService subjectService) {
        this.questionService = questionService;
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "{currentPage}/{pageSize}", method = RequestMethod.GET)
    public RestResponse getQuestionList(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        IPage<Question> page = new Page<>(currentPage, pageSize);
        IPage<Question> subjectIPage = questionService.page(page);
        IPage<QuestionVM> questionVMPage = subjectIPage.convert(question -> {
            QuestionVM questionVM = new QuestionVM(question);
            questionVM.setGradeName(subjectService.getById(question.getSubjectId()).getGradeName());
            questionVM.setSubjectName(subjectService.getById(question.getSubjectId()).getSubjectName());
            return questionVM;
        });
        return RestResponse.success(questionVMPage);
    }

    @RequestMapping(method = RequestMethod.POST)
        public RestResponse addQuestion(@RequestBody QuestionVM vm) {
        LambdaQueryWrapper<Subject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(vm.getGradeName() != null, Subject::getGradeName, vm.getGradeName());
        wrapper.eq(vm.getSubjectName() != null, Subject::getSubjectName, vm.getSubjectName());
        Question question = new Question(vm);
        question.setSubjectId(subjectService.getOne(wrapper).getId());
        questionService.save(question);
        return RestResponse.success();
    }
}
