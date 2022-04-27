package com.example.configrue.security;

import cn.hutool.json.JSONUtil;
import com.example.domain.RestResponse;
import com.example.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        response.setContentType("application/json,charset=utf-8");
        ServletOutputStream out = response.getOutputStream();

        response.setHeader(jwtUtils.getHeader(),"");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        RestResponse result = RestResponse.success("退出成功");

        out.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

        out.flush();
        out.close();
    }
}
