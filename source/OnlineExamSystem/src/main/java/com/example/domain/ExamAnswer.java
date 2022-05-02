package com.example.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ExamAnswer {
    private Integer id;
    private Integer examId;
    private Integer userScore; //用户得分
    private Integer paperScore; //试卷总分
    private Integer createrId; //创建人
    private Date createTime; //创建时间

    public ExamAnswer() {
        this.createrId = 0;
    }
    public ExamAnswer(Integer id, int examId,int userScore, Integer paperScore, int createrId) {
        this.id = id;
        this.examId = examId;
        this.userScore = userScore;
        this.paperScore = paperScore;
        this.createrId = createrId;
    }
}
