package com.policyInfo.policyInfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf().disable()    //CSRF 공격에 대한 방어를 해제
                .authorizeRequests() //URI에 따른 페이지에 대한 권한을 부여하기 위해 시작하는 메소드
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/loginProc")
                    .defaultSuccessUrl("/")
                    //.failureHandler(customFailureHandler())
                .and()
                    .logout() // 로그아웃 기능 작동함
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 시 URL 재정의
                    .logoutSuccessUrl("/") // 로그아웃 성공 후 이동페이지
                    /*.logoutSuccessHandler((request, response, authentication) -> response.sendRedirect("/"))
                    .addLogoutHandler((request, response, authentication) -> {
                        System.out.println("logout success!");
                        HttpSession session = request.getSession();
                        session.invalidate();
                    })*/
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID") // 로그아웃 후 쿠키 삭제
                .and().build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public AuthenticationFailureHandler customFailureHandler() {
        return new CustomAuthFailureHandler();
    }*/
}