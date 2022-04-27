package com.example.configrue.security;

import cn.hutool.core.util.StrUtil;
import com.example.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    JwtUtils jwtUtils;

    public MyAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt = request.getHeader(jwtUtils.getHeader());
        if(StrUtil.isBlankOrUndefined(jwt)){
            chain.doFilter(request,response);
            return;
        }

        Claims claims = jwtUtils.getClaimsBytoken(jwt);
        if(claims == null){
            throw new JwtException("token无效");
        }
        if(jwtUtils.isTokenExpired(claims)){
            throw new JwtException("token过期");
        }

        String username = claims.getSubject();

        UsernamePasswordAuthenticationToken authentication
                = new UsernamePasswordAuthenticationToken(username,null, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request,response);
    }


}
