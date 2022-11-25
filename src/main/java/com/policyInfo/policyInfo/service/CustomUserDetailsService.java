/*
package com.policyInfo.policyInfo.service;

import com.policyInfo.policyInfo.login.Member;
import com.policyInfo.policyInfo.login.MemberRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Data
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository userRepository;

    //메서드가 자동으로 불림.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email: " + username + " not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPswd(), getAuthorities(user));

    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Member user){
        String[] userRoles = user.getRoles()
                .stream()
                .map((role) -> role.getRolename())
                .toArray(String[]::new);

        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}
*/
