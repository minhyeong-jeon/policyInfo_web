package com.policyInfo.policyInfo.join;

import com.policyInfo.policyInfo.member.User;
import com.policyInfo.policyInfo.member.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public JoinController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Autowired
    UserRepository userRepository;

    @GetMapping("/join")
    public String join(Model model) {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProc(User user) {
        String rawPassword = user
                .getPassword();

        System.out.println("rawPassword = " + rawPassword);
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "redirect:/";
    }


}

