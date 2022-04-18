package com.example.viewmodel.admin;

import com.example.domain.Exam;
import lombok.Data;

@Data
public class ExamVM {
    private Integer id;
    private String examName;
    private String gradeName;
    private String subjectName;


    public ExamVM() {
    }

    public ExamVM(Exam exam) {
        this.id = exam.getId();
        this.examName = exam.getExamName();
    }
}
