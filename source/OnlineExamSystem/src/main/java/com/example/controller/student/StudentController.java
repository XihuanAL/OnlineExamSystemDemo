package com.example.controller.student;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.domain.RestResponse;
import com.example.domain.User;
import com.example.service.UserService;
import com.example.viewmodel.user.UpdatePassword;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController("studentStudentController")
@RequestMapping("/student/users")
public class StudentController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    public StudentController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public RestResponse updatePassword(@RequestBody UpdatePassword updatePassword, Principal principal) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUsername, principal.getName());
        User user = userService.getOne(queryWrapper);
        if(user == null) {
            return RestResponse.fail("用户不存在");
        }
        if(!new BCryptPasswordEncoder().matches(updatePassword.getOldPassword(), user.getPassword())) {
            return RestResponse.fail("旧密码错误");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(updatePassword.getNewPassword()));
        userService.updateById(user);
        return RestResponse.success();
    }
}
