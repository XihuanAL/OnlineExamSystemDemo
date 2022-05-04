package com.example.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.*;
import com.example.service.*;
import com.example.viewmodel.admin.ExamAnswerVM;
import com.example.viewmodel.admin.ExamVM;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController("adminExamPaperController")
@RequestMapping("/admin/exampapers")
public class ExamPaperController {
    private final UserService userService;
    private final ExamService examService;
    private final ExamAnswerService examAnswerService;
    private final SubjectService subjectService;
    private final ExamQuestionAnswerService examQuestionAnswerService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public ExamPaperController(UserService userService, ExamService examService, ExamAnswerService examAnswerService, SubjectService subjectService, ExamQuestionAnswerService examQuestionAnswerService) {
        this.userService = userService;
        this.examService = examService;
        this.examAnswerService = examAnswerService;
        this.subjectService = subjectService;
        this.examQuestionAnswerService = examQuestionAnswerService;
    }

    @RequestMapping(value = "{currentPage}/{pageSize}", method = RequestMethod.GET)
    public RestResponse getQuestionPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        IPage<ExamAnswer> page = new Page<>(currentPage, pageSize);
        IPage<ExamAnswer> examAnswerIPage = examAnswerService.page(page);
        IPage<ExamAnswerVM> examAnswerVMIPage = examAnswerIPage.convert(ExamAnswer -> {
            Exam exam = examService.getById(ExamAnswer.getExamId());
            Subject subject = subjectService.getById(exam.getSubjectId());
            User user = userService.getById(ExamAnswer.getCreaterId());
            ExamAnswerVM examAnswerVM = new ExamAnswerVM();
            examAnswerVM.setId(ExamAnswer.getId());
            examAnswerVM.setSubjectName(subject.getSubjectName());
            examAnswerVM.setGradeName(subject.getGradeName());
            examAnswerVM.setExamName(exam.getExamName());
            examAnswerVM.setExamId(exam.getId());
            examAnswerVM.setCreaterName(user.getRealName());
            examAnswerVM.setCreaterId(user.getId());
            examAnswerVM.setUserScore(ExamAnswer.getUserScore());
            examAnswerVM.setPaperScore(ExamAnswer.getPaperScore());
            examAnswerVM.setCreateTime(ExamAnswer.getCreateTime());
            return examAnswerVM;
        });
        return RestResponse.success(examAnswerVMIPage);
    }

    @RequestMapping(value = "/answers/{id}/{questionId}/{createrId}", method = RequestMethod.GET)
    public RestResponse getAnswer(@PathVariable int id, @PathVariable int questionId, @PathVariable Integer createrId) {
        ExamAnswer examAnswer = examAnswerService.getById(id);
        ExamQuestionAnswer examQuestionAnswer = examQuestionAnswerService.getOne(new LambdaQueryWrapper<ExamQuestionAnswer>().eq(ExamQuestionAnswer::getExamAnswerId, examAnswer.getId()).eq(ExamQuestionAnswer::getQuestionId, questionId));
        return RestResponse.success(examQuestionAnswer.getQuestionAnswer());
    }



}
