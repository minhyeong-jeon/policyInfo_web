package com.policyInfo.policyInfo;

import com.policyInfo.policyInfo.member.User;
import com.policyInfo.policyInfo.member.UserRepository;
import com.policyInfo.policyInfo.workAPI.ServList;
import com.policyInfo.policyInfo.workAPI.WantedList;
import com.policyInfo.policyInfo.workAPI.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkService workService;

    @GetMapping("/user")
    public @ResponseBody String user(Model model) {
        return "login";
    }

    @GetMapping("/")
    public String board(Model model, User user, Principal principal) throws UnsupportedEncodingException {
        String lifeCycle = userRepository.findByUsername(principal.getName()).getLifeCycle();
        String lifeType = userRepository.findByUsername(principal.getName()).getLifeType();

        ResponseEntity<String> responseEntity = workService.getAPI(lifeCycle, lifeType);
        WantedList response = workService.parser(responseEntity.getBody());

        //html에 객체 지정 실시
        List<ServList> tables = new ArrayList<>();
        for(int i=0; i < Integer.valueOf(response.getTotalCount()); i++) { //반복문을 수행하면서 리스트에 데이터 삽입
            String servNm = response.getServList().get(i).getServNm();
            String servDgst = response.getServList().get(i).getServDgst();

            //객체를 리스트에 삽입
            ServList servList = new ServList(servNm, servDgst);
            tables.add(servList);

            model.addAttribute("itemName",response.getServList().get(i).getServNm());
            model.addAttribute("itemCntn",response.getServList().get(i).getServDgst());

        }
        model.addAttribute("tableList",tables);
        model.addAttribute("lifeCycle", lifeCycle);
        model.addAttribute("lifeType", lifeType);

        return "main";
    }

}