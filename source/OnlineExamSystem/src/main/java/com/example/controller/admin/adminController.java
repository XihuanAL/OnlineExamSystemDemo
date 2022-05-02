package com.example.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.domain.RestResponse;
import com.example.domain.User;
import com.example.service.UserService;
import com.example.viewmodel.user.UpdatePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/admin/users")
public class adminController {
    private final UserService userService;

    @Autowired
    public adminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/currentUsername", method = RequestMethod.GET)
    public RestResponse currentUsername(Principal principal) {
        return RestResponse.success(principal.getName());
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
