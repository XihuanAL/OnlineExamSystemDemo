package com.example.configrue.security;

import cn.hutool.json.JSONUtil;
import com.example.domain.RestResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json,charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ServletOutputStream out = response.getOutputStream();


        RestResponse result = RestResponse.fail(accessDeniedException.getMessage());

        out.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

        out.flush();
        out.close();
    }
}
