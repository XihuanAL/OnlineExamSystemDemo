package com.example.configrue.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyAuthenticationProvider myAuthenticationProvider;
    private final MyLoginSuccessHandler myLoginSuccessHandler;
    private final MyAccessDeniedHandler myAccessDeniedHandler;
    private final MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    private final MyLogoutSuccessHandler myLogoutSuccessHandler;
    private final MyLoginFailureHandler myLoginFailureHandler;
    @Bean
    MyAuthenticationFilter myAuthenticationFilter() throws Exception {
        return new MyAuthenticationFilter(authenticationManager());
    }

    @Autowired
    public SecurityConfig(MyAuthenticationProvider myAuthenticationProvider, MyLoginSuccessHandler myLoginSuccessHandler, MyAccessDeniedHandler myaccessDeniedHandler, MyAuthenticationEntryPoint myAuthenticationEntryPoint, MyLogoutSuccessHandler myLogoutSuccessHandler, MyLoginFailureHandler myLoginFailureHandler) {
        this.myAuthenticationProvider = myAuthenticationProvider;
        this.myLoginSuccessHandler = myLoginSuccessHandler;
        this.myAccessDeniedHandler = myaccessDeniedHandler;
        this.myAuthenticationEntryPoint = myAuthenticationEntryPoint;
        this.myLogoutSuccessHandler = myLogoutSuccessHandler;
        this.myLoginFailureHandler = myLoginFailureHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(myAuthenticationProvider)
                .formLogin().permitAll()
                .successHandler(myLoginSuccessHandler)
                .failureHandler(myLoginFailureHandler)

                .and()
                .logout()
                .logoutSuccessHandler(myLogoutSuccessHandler)
                //??????
                .and().authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/student/**").hasRole("STUDENT")
                .anyRequest().hasRole("ADMIN")

                //???????????????
                .and()
                .exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler)
                .authenticationEntryPoint(myAuthenticationEntryPoint)

                .and()
                .addFilter(myAuthenticationFilter())
                .cors()
                .and().csrf().disable();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // ??????????????????
        CorsConfiguration configuration = new CorsConfiguration();
        //configuration.setAllowedOrigins(Arrays.asList("*"));//?????????????????????
        configuration.addAllowedOriginPattern("*");//?????????????????????
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*")); // ?????????????????????

        // ????????????cookie
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean // ????????????????????????
    PasswordEncoder password(){
        return new BCryptPasswordEncoder();
    }

    public MyUsernameFilter authenticationFilter() {
        return new MyUsernameFilter();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
