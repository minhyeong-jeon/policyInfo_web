package com.policyInfo.policyInfo.login;

import com.policyInfo.policyInfo.member.User;
import com.policyInfo.policyInfo.member.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new MemberDetails(user);
        }
        return null;
    }
}

