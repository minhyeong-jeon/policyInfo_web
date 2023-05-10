/*

package com.policyInfo.policyInfo.login;

import com.policyInfo.policyInfo.member.MemberRole;
import com.policyInfo.policyInfo.member.User;
import com.policyInfo.policyInfo.member.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


*/
/* UserDetailsService 인터페이스 : db에서 회원정보를 가져오는 역할
*   loadUserByUserName() 메소드가 존재
*   회원 정보를 조회하여 사용자의 권한을 갖는 UserDetail 인터페이스를 반환*//*

@RequiredArgsConstructor
@Transactional
@Service
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    */
/*@Override
    public UserDetails loadUserByUsername(String nickName) {
        User user = userRepository.findByNickName(nickName);
        if (user != null) {
            return new MemberDetails(user);
        }
        return null;
    }*//*


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User member = userRepository.findByUsername(username);
        String role = member.getRole().toString();

        if (member == null) {
            throw new UsernameNotFoundException(username);
        }

        return (UserDetails) User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .role(member.getRole())
                .build();
    }

*/
/*    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {

        //adminUser 정보 조회
        Optional<User> adminUser = userRepository.findByNickName(adminId);

        if(adminUser.isPresent()) {
            User admin = adminUser.get();

            User authAdmin = User.builder()
                    .nickName(admin.getNickName())
                    .password(admin.getPassword())
                    .role(admin.getRole())
                    .build();

            return authAdmin;
        }
        return null;
    }*//*


*/
/*    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByNickName(username);

        System.out.println("userDetail = " + user);

        return new MemberDetails(user);
    }*//*


}


*/
