package com.policyInfo.policyInfo.member;

import com.policyInfo.policyInfo.join.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor //final이나 @NonNull이 붙은 필드에 생성자를 생성
@Transactional //로직을 처리하다가 에러가 발생하면, 변경된 데이터를 로직을 수행하기 이전 상태로 콜백 시켜줍니다.
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);

        return userRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = userRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = userRepository.findByEmail(email);
        System.out.println("email121213 : "+email);
        System.out.println("membermembermember : "+member);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
