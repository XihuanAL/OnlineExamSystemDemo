package com.example.viewmodel.admin;

import com.example.domain.Question;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ExamAnswerVM {
    private Integer id;
    private String subjectName;
    private String gradeName;
    private String examName;
    private Integer examId;
    private String createrName;
    private Integer createrId;
    private Integer userScore;
    private Integer paperScore;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
