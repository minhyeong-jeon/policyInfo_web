package com.policyInfo.policyInfo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.Response;

@Controller
public class HomeController {

    @GetMapping("/")
    public String main(Model model){
        return "index";
    }


    @GetMapping("/board")
    public String board(Model model){
        return "dashboard";
    }

}
