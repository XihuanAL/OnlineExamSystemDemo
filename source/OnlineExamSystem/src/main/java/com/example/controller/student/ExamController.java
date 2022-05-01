package com.example.controller.student;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.*;
import com.example.service.ExamQuestionService;
import com.example.service.ExamService;
import com.example.service.QuestionService;
import com.example.service.SubjectService;
import com.example.viewmodel.admin.ExamVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController("StudentExamController")
@RequestMapping("/student/exams")
public class ExamController {
    private final ExamService examService;
    private final SubjectService subjectService;
    private final ExamQuestionService examQuestionService;
    private final QuestionService questionService;
    private final Logger logger = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    public ExamController(ExamService examService, SubjectService subjectService, ExamQuestionService examQuestionService, QuestionService questionService) {
        this.examService = examService;
        this.subjectService = subjectService;
        this.examQuestionService = examQuestionService;
        this.questionService = questionService;
    }


    @RequestMapping(value = "{currentPage}/{pageSize}", method = RequestMethod.GET)
    public RestResponse getQuestionPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        IPage<Exam> page = new Page<>(currentPage, pageSize);
        IPage<Exam> subjectIPage = examService.page(page);
        IPage<ExamVM> examVMIPage = subjectIPage.convert(Exam -> {
            ExamVM examVM = new ExamVM(Exam);
            examVM.setGradeName(subjectService.getById(Exam.getSubjectId()).getGradeName());
            examVM.setSubjectName(subjectService.getById(Exam.getSubjectId()).getSubjectName());
            List<Integer> questionIds = new ArrayList<>();
            examQuestionService.list(new LambdaQueryWrapper<ExamQuestion>().eq(ExamQuestion::getExamId, Exam.getId())).forEach(examQuestion -> {
                questionIds.add(examQuestion.getQuestionId());
            });
            List<Question> questions = questionService.list(new LambdaQueryWrapper<Question>().in(!questionIds.isEmpty() , Question::getId, questionIds));
            examVM.setQuestionList(questions);
            return examVM;
        });
        return RestResponse.success(examVMIPage);
    }

    @RequestMapping(method = RequestMethod.POST)
    public RestResponse addExam(@RequestBody ExamVM examVM) {
        LambdaQueryWrapper<Subject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Subject::getGradeName, examVM.getGradeName());
        wrapper.eq(Subject::getSubjectName, examVM.getSubjectName());
        Subject subject = subjectService.getOne(wrapper);
        Exam exam = new Exam(examVM.getExamName(), subject.getId());
        examService.save(exam);
        examQuestionService.saveBatch(examVM.getQuestionList().stream().map(question -> {
            return new ExamQuestion(exam.getId(), question.getId());
        }).collect(Collectors.toCollection(ArrayList::new)));
        return RestResponse.success();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public RestResponse deleteExamById(@PathVariable Integer id) {
        examService.removeById(id);
        examQuestionService.remove(new LambdaQueryWrapper<ExamQuestion>().eq(ExamQuestion::getExamId, id));
        return RestResponse.success();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public RestResponse updateExam(@RequestBody ExamVM examVM) {
        LambdaQueryWrapper<Subject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Subject::getGradeName, examVM.getGradeName());
        wrapper.eq(Subject::getSubjectName, examVM.getSubjectName());
        Subject subject = subjectService.getOne(wrapper);
        Exam exam = new Exam(examVM.getExamName(), subject.getId());
        exam.setId(examVM.getId());
        examService.updateById(exam);
        examQuestionService.remove(new LambdaQueryWrapper<ExamQuestion>().eq(ExamQuestion::getExamId, exam.getId()));
        examQuestionService.saveBatch(examVM.getQuestionList().stream().map(question -> {
            return new ExamQuestion(exam.getId(), question.getId());
        }).collect(Collectors.toCollection(ArrayList::new)));
        return RestResponse.success();
    }
}
