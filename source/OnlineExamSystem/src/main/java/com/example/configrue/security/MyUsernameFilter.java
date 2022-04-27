package com.example.configrue.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;


public class MyUsernameFilter extends UsernamePasswordAuthenticationFilter {
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login", "POST");
    private String usernameParameter = "username";
    private String passwordParameter = "password";
    private boolean postOnly = true;

    private final Logger logger = Logger.getLogger(MyUsernameFilter.class.getName());


    public MyUsernameFilter() {
        super();
    }
    public MyUsernameFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username = request.getParameter("username");
        logger.info("username: " + username);
        if (username == null) {
            throw new AuthenticationServiceException("Username is empty");
        }
        return username;
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String password = request.getParameter("password");
        logger.info("password: " + password);
        if (password == null) {
            throw new AuthenticationServiceException("Password is empty");
        }
        return password;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
