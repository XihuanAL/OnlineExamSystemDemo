package com.example.configrue.security;

import com.example.domain.RestResponse;
import com.example.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import cn.hutool.json.JSONUtil;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

@Component
public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setContentType("application/json,charset=utf-8");
        ServletOutputStream out = response.getOutputStream();

        //生成token并放入请求头
        String jwt = jwtUtils.generateToken(authentication.getName());
        response.setHeader(jwtUtils.getHeader(),jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

//        String role = authentication.getAuthorities().toString();
//        response.setHeader("role",role);
//        response.setHeader("Access-Control-Expose-Headers", "role");
        Iterator<? extends GrantedAuthority> it= authentication.getAuthorities().iterator();
        RestResponse result = RestResponse.success(it.next().toString());

        out.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

        out.flush();
        out.close();
    }
}
