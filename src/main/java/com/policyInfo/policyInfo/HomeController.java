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

        if(lifeCycle.equals("선택안함"))
            lifeCycle=null;
        if(lifeType.equals("선택안함"))
            lifeType=null;
        //DB에 저장된 복지 API 가져오기
        //List<WantedList> wantedList = publicAPIRepository.findResultMessage();
        //System.out.println("wantedList = " + wantedList);

        //List<ServList> servListList = servAPIRepository.findByLifeArrayContaining(lifeCycle);
        List<ServList> servListList = servAPIRepository.findByLifeArrayContainingAndTrgterIndvdlArrayContaining(lifeCycle, lifeType);
        List<ServList> servLists = servAPIRepository.findByTrgterIndvdlArrayContaining(lifeType);
        //List<ServList> tables = new ArrayList<>();
        //System.out.println("wantedList.get(0).getTotalCount() = " + wantedList.get(0).getTotalCount());
        System.out.println("servList = " + servListList);

        /*for(int i = 0; i < servListList.size(); i++){
            System.out.println("servListList = " + servListList.get(i));
            if(servListList.get(i).getLifeArray().contains(lifeCycle)||servListList.get(i).getTrgterIndvdlArray().contains(lifeType)){
                servNm = servListList.get(i).getServNm();
                servDgst = servListList.get(i).getServDgst();

                ServList servList = new ServList(servNm, servDgst);
                tables.add(servList);

                model.addAttribute("itemName",servNm);
                model.addAttribute("itemName",servDgst);f



            }
        }*/
        /*model.addAttribute("tableList",tables);
        model.addAttribute("lifeCycle", lifeCycle);
        model.addAttribute("lifeType", lifeType);*/

        //ResponseEntity<String> responseEntity = publicAPIParser.getAPI();
        //WantedList response = publicAPIParser.parser(responseEntity.getBody());

        //System.out.println("wantedList = " + wantedList.getServList());
        //html에 객체 지정 실시
        /*List<ServList> tables = new ArrayList<>();
        for(int i=0; i < Integer.valueOf(wantedList.getTotalCount()); i++) { //반복문을 수행하면서 리스트에 데이터 삽입
            String servNm = wantedList.getServList().get(i).getServNm();
            String servDgst = wantedList.getServList().get(i).getServDgst();

            System.out.println("wantedList = " + wantedList.getServList().get(i));

            //객체를 리스트에 삽입
            ServList servList = new ServList(servNm, servDgst);
            tables.add(servList);

            model.addAttribute("itemName",servNm);
            model.addAttribute("itemCntn",servDgst);

        }*/

        /*model.addAttribute("tableList",tables);
        model.addAttribute("lifeCycle", lifeCycle);
        model.addAttribute("lifeType", lifeType);*/

        //System.out.println("id = " + id);

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