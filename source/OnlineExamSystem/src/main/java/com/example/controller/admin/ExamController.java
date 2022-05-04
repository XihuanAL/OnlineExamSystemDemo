package com.example.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.*;
import com.example.service.*;
import com.example.viewmodel.admin.ExamVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exams")
public class ExamController {
    private final ExamService examService;
    private final SubjectService subjectService;
    private final ExamQuestionService examQuestionService;
    private final QuestionService questionService;
    private final ExamAnswerService examAnswerService;
    private final ExamQuestionAnswerService examQuestionAnswerService;
    private final Logger logger = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    public ExamController(ExamService examService, SubjectService subjectService, ExamQuestionService examQuestionService, QuestionService questionService, ExamAnswerService examAnswerService, ExamQuestionAnswerService examQuestionAnswerService) {
        this.examService = examService;
        this.subjectService = subjectService;
        this.examQuestionService = examQuestionService;
        this.questionService = questionService;
        this.examAnswerService = examAnswerService;
        this.examQuestionAnswerService = examQuestionAnswerService;
    }

    //获取试卷列表
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
            List<Question> questions = questionService.list(new LambdaQueryWrapper<Question>().in(!questionIds.isEmpty(), Question::getId, questionIds));
            examVM.setQuestionList(questions);
            return examVM;
        });
        return RestResponse.success(examVMIPage);
    }

    //新增试卷
    @RequestMapping(method = RequestMethod.POST)
    public RestResponse addExam(@RequestBody ExamVM examVM) {
        LambdaQueryWrapper<Subject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Subject::getGradeName, examVM.getGradeName());
        wrapper.eq(Subject::getSubjectName, examVM.getSubjectName());
        Subject subject = subjectService.getOne(wrapper);
        int paperScore = 0;
        for(Question question : examVM.getQuestionList()){
            paperScore += question.getScore();
        }
        Exam exam = new Exam(examVM.getExamName(), subject.getId(), paperScore);
        examService.save(exam);
        examQuestionService.saveBatch(examVM.getQuestionList().stream().map(question -> {
            return new ExamQuestion(exam.getId(), question.getId());
        }).collect(Collectors.toCollection(ArrayList::new)));
        return RestResponse.success();
    }

    //删除试卷
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public RestResponse deleteExamById(@PathVariable Integer id) {
        examService.removeById(id);
        examQuestionService.remove(new LambdaQueryWrapper<ExamQuestion>().eq(ExamQuestion::getExamId, id));
        examAnswerService.list(new LambdaQueryWrapper<ExamAnswer>().eq(ExamAnswer::getExamId, id)).forEach(examAnswer -> {
            examQuestionAnswerService.remove(new LambdaQueryWrapper<ExamQuestionAnswer>().eq(ExamQuestionAnswer::getExamAnswerId, examAnswer.getId()));
                });
        examAnswerService.remove(new LambdaQueryWrapper<ExamAnswer>().eq(ExamAnswer::getExamId, id));
        return RestResponse.success();
    }

    //修改试卷
    @RequestMapping(method = RequestMethod.PUT)
    public RestResponse updateExam(@RequestBody ExamVM examVM) {
        LambdaQueryWrapper<Subject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Subject::getGradeName, examVM.getGradeName());
        wrapper.eq(Subject::getSubjectName, examVM.getSubjectName());
        Subject subject = subjectService.getOne(wrapper);
        int paperScore = 0;
        for(Question question : examVM.getQuestionList()){
            paperScore += question.getScore();
        }
        Exam exam = new Exam(examVM.getExamName(), subject.getId(), paperScore);
        exam.setId(examVM.getId());
        examService.updateById(exam);
        examQuestionService.remove(new LambdaQueryWrapper<ExamQuestion>().eq(ExamQuestion::getExamId, exam.getId()));
        examQuestionService.saveBatch(examVM.getQuestionList().stream().map(question -> {
            return new ExamQuestion(exam.getId(), question.getId());
        }).collect(Collectors.toCollection(ArrayList::new)));
        return RestResponse.success();
    }

    @RequestMapping(value = "/questions/{examId}", method = RequestMethod.GET)
    public RestResponse getQuestionList(@PathVariable Integer examId) {
        List<Question> questions = examQuestionService.list(new LambdaQueryWrapper<ExamQuestion>().eq(ExamQuestion::getExamId, examId)).stream().map(examQuestion -> {
            return questionService.getById(examQuestion.getQuestionId());
        }).collect(Collectors.toCollection(ArrayList::new));
        return RestResponse.success(questions);
    }
}
