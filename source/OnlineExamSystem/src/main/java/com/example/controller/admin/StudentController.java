package com.example.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.RestResponse;
import com.example.domain.User;
import com.example.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class StudentController {
    private final UserService userService;
    private static final String STUDENT_DEFAULT_PASSWORD = "123456";
    private static final String STUDENT_DEFAULT_ROLE = "STUDENT";

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

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public RestResponse<String> deleteStudent(@PathVariable Integer id) {
        val result = userService.removeById(id);
        return RestResponse.success(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    public RestResponse<String> addStudent(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(STUDENT_DEFAULT_PASSWORD));
        user.setRole(STUDENT_DEFAULT_ROLE);
        val result = userService.save(user);
        return RestResponse.success(result);
    }
}