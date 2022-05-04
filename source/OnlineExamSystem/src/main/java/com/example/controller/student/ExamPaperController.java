package com.example.controller.student;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.domain.*;
import com.example.service.ExamAnswerService;
import com.example.service.ExamQuestionAnswerService;
import com.example.service.UserService;
import com.example.viewmodel.admin.ExamVM;
import com.example.viewmodel.student.ExamPaperVM;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/student/exampapers")
public class ExamPaperController {
    private final UserService userService;
    private final ExamAnswerService examAnswerService;
    private final ExamQuestionAnswerService examQuestionAnswerService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ExamPaperController(ExamAnswerService examAnswerService, ExamQuestionAnswerService examQuestionAnswerService, UserService userService) {
        this.userService = userService;
        this.examAnswerService = examAnswerService;
        this.examQuestionAnswerService = examQuestionAnswerService;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public RestResponse commitExamPaper(@RequestBody ExamPaperVM examPaperVM, Principal principal){
        ExamAnswer examAnswer = new ExamAnswer();
        examAnswerService.save(examAnswer); //获取id

        int userScore = 0;
        Iterator<String> answer = examPaperVM.getAnswerList().iterator();
        for(Question question : examPaperVM.getQuestionList()){
            String ans = answer.next();
            ExamQuestionAnswer examQuestionAnswer = new ExamQuestionAnswer(examAnswer.getId(), question.getId(), ans);
            if(question.getCorrect().equals(ans)){
                userScore+=question.getScore();
                examQuestionAnswer.setDoRight(1);
            }else examQuestionAnswer.setDoRight(0);
            examQuestionAnswerService.save(examQuestionAnswer);
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUsername, principal.getName());
        int createrId = userService.getOne(queryWrapper).getId();

        //logger.info(examPaperVM.getAnswerList().toString());
        examAnswer = new ExamAnswer(examAnswer.getId(), examPaperVM.getId(), userScore, examPaperVM.getPaperScore(), createrId);
        examAnswerService.updateById(examAnswer);
        return RestResponse.success("得分"+userScore);
    }

    @RequestMapping(value = "/answers/{examId}/{questionId}", method = RequestMethod.GET)
    public RestResponse getAnswer(@PathVariable int examId, @PathVariable int questionId, Principal principal){
        ExamAnswer examAnswer = examAnswerService.getOne(new LambdaQueryWrapper<ExamAnswer>().eq(ExamAnswer::getExamId, examId).eq(ExamAnswer::getCreaterId, userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, principal.getName())).getId()));
        ExamQuestionAnswer examQuestionAnswer = examQuestionAnswerService.getOne(new LambdaQueryWrapper<ExamQuestionAnswer>().eq(ExamQuestionAnswer::getExamAnswerId, examAnswer.getId()).eq(ExamQuestionAnswer::getQuestionId, questionId));
        return RestResponse.success(examQuestionAnswer.getQuestionAnswer());
    }

    @RequestMapping(value = "/score/{examId}", method = RequestMethod.GET)
    public RestResponse getScore(@PathVariable int examId, Principal principal){
        ExamAnswer examAnswer = examAnswerService.getOne(new LambdaQueryWrapper<ExamAnswer>().eq(ExamAnswer::getExamId, examId).eq(ExamAnswer::getCreaterId, userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, principal.getName())).getId()));
        return RestResponse.success(examAnswer.getUserScore());
    }
}
