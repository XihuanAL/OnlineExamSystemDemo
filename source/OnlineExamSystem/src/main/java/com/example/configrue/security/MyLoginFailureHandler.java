package com.example.configrue.security;

import cn.hutool.json.JSONUtil;
import com.example.domain.RestResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json,charset=utf-8");
        ServletOutputStream out = response.getOutputStream();


        RestResponse result = RestResponse.success(exception.getMessage());

        out.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

        out.flush();
        out.close();
    }
}
