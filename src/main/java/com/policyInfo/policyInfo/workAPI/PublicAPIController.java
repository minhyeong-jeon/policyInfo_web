package com.policyInfo.policyInfo.workAPI;

import com.policyInfo.policyInfo.member.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class PublicAPIController {

    @Autowired
    PublicAPIRepository publicAPIRepository;

    @Autowired
    PublicAPIParser publicAPIParser;


    @GetMapping("/saveTest")
    public String saveTest() {
        return "dbSaveTest";
    }

    @PostMapping("/lib")
    public String saveTest2(ServList servList) throws UnsupportedEncodingException {

        ResponseEntity<String> responseEntity = publicAPIParser.getAPI();
        WantedList response = publicAPIParser.parser(responseEntity.getBody());


        //publicAPIRepository.deleteAll();
        publicAPIRepository.save(response);
        return "redirect:/";


    }
}
