package com.example.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.RestResponse;
import com.example.domain.User;
import com.example.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final UserService userService;

    @Autowired
    public StudentController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "{currentPage}/{pageSize}",method = RequestMethod.GET)
    public RestResponse<IPage<User>> getStudentsPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        IPage<User> page = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getRole, "STUDENT");
        IPage<User> StudentIPage = userService.page(page, wrapper);
        return RestResponse.success(StudentIPage);
    }
}