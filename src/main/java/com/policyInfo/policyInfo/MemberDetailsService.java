package com.policyInfo.policyInfo;

import com.policyInfo.policyInfo.login.User;
import com.policyInfo.policyInfo.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Principal;

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

    public String currentUserName(Principal principal) {
        return principal.getName();
    }
}

