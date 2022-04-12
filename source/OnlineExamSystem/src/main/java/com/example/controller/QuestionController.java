package com.example.controller;

import com.example.domain.Question;
import com.example.domain.RestResponse;
import com.example.domain.Subject;
import com.example.service.ContentService;
import com.example.service.QuestionService;
import com.example.service.SubjectService;
import com.example.viewmodel.admin.QuestionVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private QuestionService questionService;
    private ContentService  contentService;
    private SubjectService subjectService;

    @Autowired
    public QuestionController(QuestionService questionService, SubjectService subjectService) {
        this.questionService = questionService;
        this.subjectService = subjectService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public RestResponse getQuestionList() {
        List<Question> questions = questionService.list();
        List<QuestionVM> vms = new ArrayList<>();
        questions.forEach(question -> {
            Subject subject = subjectService.getById(question.getSubjectId());
            QuestionVM vm = new QuestionVM(subject, question);
            vms.add(vm);
        });
         return RestResponse.success(vms);
    }
}
