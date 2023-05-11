package com.policyInfo.policyInfo.join;

import com.policyInfo.policyInfo.member.Member;
import com.policyInfo.policyInfo.member.UserRepository;
import com.policyInfo.policyInfo.member.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/members")
public class JoinController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

   /* @Autowired
    public JoinController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }*/

    @Autowired
    UserService userService;


    @GetMapping(value = "/join")
    public String join(Model model) {
        model.addAttribute("userDto", new UserDto());

        return "join";
    }

    @PostMapping(value = "join")
    public String memberForm(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "join";
        }

        try {
            Member member = Member.createMember(userDto, bCryptPasswordEncoder);
            userService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            System.out.println("e.getMessage(): "+e.getMessage());
            return "join";
        }

        return "redirect:/";
    }
}

