package com.policyInfo.policyInfo;

import com.policyInfo.policyInfo.member.UserRepository;
import com.policyInfo.policyInfo.workAPI.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@NoArgsConstructor
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PublicAPIRepository publicAPIRepository;

    @Autowired
    ServAPIRepository servAPIRepository;


    @Autowired
    PublicAPIParser publicAPIParser;

    String servNm, servDgst;

    @GetMapping("/user")
    public @ResponseBody String user(Model model) {
        return "login";
    }

    @GetMapping("/")
    @Transactional
    public String board(Model model, Principal principal) throws UnsupportedEncodingException {

        //사용자의 생애주기와 가구유형 가져오기
        String lifeCycle = userRepository.findByUsername(principal.getName()).getLifeCycle();
        String lifeType = userRepository.findByUsername(principal.getName()).getLifeType();
        List<ServList> servLists = new ArrayList<>();

        //DB에 저장된 복지 API 가져오기
        if(lifeCycle.equals("선택안함")){
            servLists = servAPIRepository.findByTrgterIndvdlArrayContaining(lifeType);
        }

        else if(lifeType.equals("선택안함")){
            servLists = servAPIRepository.findByLifeArrayContaining(lifeCycle);
        }
        else if(lifeType.equals("선택안합") && lifeCycle.equals("선택안함")){
            servLists = servAPIRepository.findAll();
        }
        else{
            servLists = servAPIRepository.findByLifeArrayContainingAndTrgterIndvdlArrayContaining(lifeCycle,lifeType);
        }

        List<ServList> tables = new ArrayList<>();
        for(int i = 0; i < servLists.size(); i++){

            servNm = servLists.get(i).getServNm();
            servDgst = servLists.get(i).getServDgst();

            ServList servList = new ServList(servNm, servDgst);
            tables.add(servList);

            model.addAttribute("itemName",servNm);
            model.addAttribute("itemName",servDgst);
        }

        model.addAttribute("tableList",tables);
        model.addAttribute("lifeCycle", lifeCycle);
        model.addAttribute("lifeType", lifeType);

        return "main";
    }

    @GetMapping("/test")
    public String apiTest(Model model) throws UnsupportedEncodingException {
        ResponseEntity<String> responseEntity = publicAPIParser.getAPI();
        WantedList response = publicAPIParser.parser(responseEntity.getBody());

        model.addAttribute("response", response);

        return "test";
    }

}