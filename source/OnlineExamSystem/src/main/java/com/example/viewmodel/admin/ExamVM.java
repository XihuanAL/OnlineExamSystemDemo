package com.example.viewmodel.admin;

import com.example.domain.Exam;
import com.example.domain.Question;
import lombok.Data;

import java.util.List;

@Data
public class ExamVM {
    private Integer id;
    private String examName;
    private String gradeName;
    private String subjectName;
    private List<Question> questionList;

    public ExamVM() {
    }

    public ExamVM(Exam exam) {
        this.id = exam.getId();
        this.examName = exam.getExamName();
    }
}
