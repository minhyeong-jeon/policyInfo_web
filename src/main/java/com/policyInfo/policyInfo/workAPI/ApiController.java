package com.policyInfo.policyInfo.workAPI;

import com.policyInfo.policyInfo.member.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApiController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServAPIRepository servAPIRepository;


    @Autowired
    ApiService apiServiceImpl;



    @GetMapping("/")
    @Transactional
    public String board(Model model, Principal principal) throws UnsupportedEncodingException {

        ResponseEntity<String> responseEntity = apiServiceImpl.getAPI();
        WantedList response = apiServiceImpl.parser(responseEntity.getBody());

        List<ServList> servLists = new ArrayList<>();
        List<ServList> tables = new ArrayList<>();

        String lifeCycle;
        String lifeType;

        String servNm;
        String servDgst;

        //if(auth.getPrincipal() != null) {
        if(principal != null) {

            //사용자의 생애주기와 가구유형 가져오기
            lifeCycle = userRepository.findByUsername(principal.getName()).getLifeCycle();
            lifeType = userRepository.findByUsername(principal.getName()).getLifeType();

            //DB에 저장된 복지 API 가져오기
            if (lifeCycle.equals("선택안함")) {
                servLists = servAPIRepository.findByTrgterIndvdlArrayContaining(lifeType);
            } else if (lifeType.equals("선택안함")) {
                servLists = servAPIRepository.findByLifeArrayContaining(lifeCycle);
            } else if (lifeType.equals("선택안합") && lifeCycle.equals("선택안함")) {
                servLists = servAPIRepository.findAll();
            } else {
                servLists = servAPIRepository.findByLifeArrayContainingAndTrgterIndvdlArrayContaining(lifeCycle, lifeType);
            }


            model.addAttribute("user", principal);


        }
        else{
            servLists = response.getServList();

            lifeCycle = "선택안함";
            lifeType = "선택안함";
        }

        for (int i = 0; i < servLists.size(); i++) {

            servNm = servLists.get(i).getServNm();
            servDgst = servLists.get(i).getServDgst();

            ServList servList = new ServList(servNm, servDgst);
            tables.add(servList);

            model.addAttribute("itemName", servNm);
            model.addAttribute("itemName", servDgst);
        }

        model.addAttribute("tableList", tables);
        model.addAttribute("lifeCycle", lifeCycle);
        model.addAttribute("lifeType", lifeType);

        System.out.println("model : "+model.getAttribute("user"));
        return "main";
    }


    @GetMapping("/detail")
    @Transactional
    public String detail(Model model, Principal principal) throws UnsupportedEncodingException {

        WantedDetail wantedDetail = new WantedDetail();
        ResponseEntity<String> responseEntity = apiServiceImpl.getApiDetail(wantedDetail);

        return "detail";
    }


}
