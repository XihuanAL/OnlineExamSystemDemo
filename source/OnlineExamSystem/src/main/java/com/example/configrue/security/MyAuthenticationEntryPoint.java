package com.example.configrue.security;

import cn.hutool.json.JSONUtil;
import com.example.domain.RestResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json,charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ServletOutputStream out = response.getOutputStream();


        RestResponse result = RestResponse.fail("未登录");

        out.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

        out.flush();
        out.close();
    }
}
