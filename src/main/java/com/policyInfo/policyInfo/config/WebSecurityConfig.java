package com.policyInfo.policyInfo.config;

import com.policyInfo.policyInfo.member.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration  // 빈등록 (IoC관리)
@EnableWebSecurity  //security 필터 등록
@RequiredArgsConstructor
public class WebSecurityConfig {


    @Autowired
    UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf().disable()    //CSRF 공격에 대한 방어를 해제
                /*.authorizeRequests() //URI에 따른 페이지에 대한 권한을 부여하기 위해 시작하는 메소드
                .antMatchers("/**","/").authenticated()
                .antMatchers("/api/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()*/
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    //.loginProcessingUrl("/loginProc")
                    .usernameParameter("email")	// login에 필요한 id 값을 email로 설정 (default는 username)
                    //.failureHandler(authenticationFailureHandler())
                    .failureUrl("/login/error")
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
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*@Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.userService);
        provider.setPasswordEncoder(this.bCryptPasswordEncoder());
        System.out.println("provider : "+provider);
        return provider;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(userDetailsService, passwordEncoder());
    }*/
}