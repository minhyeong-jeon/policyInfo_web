package com.policyInfo.policyInfo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {//extends WebSecurityConfigurerAdapter {

    /* @Override
     protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable();
         http.authorizeRequests()
                 .antMatchers("/user/**").authenticated()
                 .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                 .anyRequest().permitAll()
                 .and()
                 .formLogin()
                 .loginPage("/login")
                 .loginProcessingUrl("/loginProc")
                 .defaultSuccessUrl("/");
     } */
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
                .defaultSuccessUrl("/board")
                .and().build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}