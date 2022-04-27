package com.example.configrue.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.controller.ExamController;
import com.example.domain.Student;
import com.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private final StudentService studentService;
    private final Logger logger = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    public MyUserDetailsService(StudentService studentService) {
        this.studentService = studentService;
    }
    @Override
    public User loadUserByUsername(String username) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getUsername, username);
        Student student = studentService.getOne(queryWrapper);
        if(student == null){
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        //logger.info(new BCryptPasswordEncoder().encode(student.getPassword()));
        return new User(username, student.getPassword(), authorities);
    }
}
