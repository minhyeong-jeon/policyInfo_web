package com.policyInfo.policyInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/main")
    public String main(Model model){
        return "index";
    }


    @GetMapping("/board")
    public String board(Model model){
        return "dashboard";
    }
}
