package com.example.viewmodel.student;

import com.example.domain.Question;
import com.example.viewmodel.admin.ExamVM;
import lombok.Data;

import java.util.List;

@Data
public class ExamPaperVM {
    private Integer id;
    private String examName;
    private String gradeName;
    private String subjectName;
    private Integer paperScore;
    private List<Question> questionList;
    private List<String> answerList;

    public ExamPaperVM() {
    }
}
