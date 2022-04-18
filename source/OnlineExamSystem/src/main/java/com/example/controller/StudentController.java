package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.RestResponse;
import com.example.domain.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "{currentPage}/{pageSize}",method = RequestMethod.GET)
    public RestResponse<IPage<Student>> getStudentsPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        IPage<Student> page = new Page<>(currentPage, pageSize);
        IPage<Student> StudentIPage = studentService.page(page);
        return RestResponse.success(StudentIPage);
    }
}