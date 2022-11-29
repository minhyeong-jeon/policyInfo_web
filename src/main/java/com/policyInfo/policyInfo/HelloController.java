package com.policyInfo.policyInfo;

import com.policyInfo.policyInfo.login.User;
import com.policyInfo.policyInfo.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.Response;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/user")
    public @ResponseBody String user(Model model) {
        return "user";
    }

    @GetMapping("/join")
    public String join(Model model) {
        return "join";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/joinProc")
    public String joinProc(User user) {
        String rawPassword = user
                .getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String board(Model model, User user, Principal principal) {
        String lifeCycle = userRepository.findByUsername(principal.getName()).getLifeCycle();
        String lifeType = userRepository.findByUsername(principal.getName()).getLifeType();

        if(lifeCycle == null && lifeType==null){
            lifeCycle = "정보없음";
            lifeType = "정보없음";
        }

        System.out.println("lifeCycle = " + lifeCycle);
        model.addAttribute("lifeCycle", lifeCycle);
        model.addAttribute("lifeType", lifeType);

        return "dashboard";
    }

}