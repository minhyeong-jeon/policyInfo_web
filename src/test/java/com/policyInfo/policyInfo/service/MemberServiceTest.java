package com.policyInfo.policyInfo.service;

import com.policyInfo.policyInfo.login.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

/*    public Member createMember() {
        MemberFormDto memberFormDto = MemberFormDto.builder()
                .email("test@email.com")
                .name("테스트")
                .address("서울시 강서구")
                .password("1111")
                .build();
        return Member.createMember(memberFormDto, passwordEncoder);
    }*/

/*    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() {
        Member member = createMember();
        Member savedMember = memberService.saveMember(member);

        assertEquals(member.getEmail(), savedMember.getEmail());
    }*/
}