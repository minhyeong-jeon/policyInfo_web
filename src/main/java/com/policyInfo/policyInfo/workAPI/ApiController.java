package com.policyInfo.policyInfo.workAPI;

import com.policyInfo.policyInfo.member.Favorite;
import com.policyInfo.policyInfo.member.FavoriteItem;
import com.policyInfo.policyInfo.member.UserRepository;
import com.policyInfo.policyInfo.workAPI.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApiController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FavoriteRepository favoriteRepository;


    @Autowired
    ApiService apiServiceImpl;



    @GetMapping("/")
    @Transactional
    public String board(Model model, Principal principal) throws UnsupportedEncodingException {


        String lifeCycle;
        String lifeType;

        if(principal != null) {

            //사용자의 생애주기와 가구유형 가져오기
            lifeCycle = userRepository.findByUsername(principal.getName()).getLifeCycle();
            lifeType = userRepository.findByUsername(principal.getName()).getLifeType();

            model.addAttribute("user", principal);
        }
        else {

            lifeCycle = "선택안함";
            lifeType = "선택안함";
        }

        ResponseEntity<String> responseEntity = apiServiceImpl.getAPI(lifeCycle, lifeType);
        WantedList response = apiServiceImpl.parser(responseEntity.getBody());

        List<ServList> servLists = new ArrayList<>();
        List<ServList> tables = new ArrayList<>();


        String servNm;
        String servDgst;
        String servId;

        //if(auth.getPrincipal() != null) {
        /*if(principal != null) {

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
        }*/
        servLists = response.getServList();

        for (int i = 0; i < servLists.size(); i++) {

            servNm = servLists.get(i).getServNm();
            servDgst = servLists.get(i).getServDgst();
            servId = servLists.get(i).getServId();

            ServList servList = new ServList(servNm, servDgst, servId);
            tables.add(servList);
        }

        model.addAttribute("tableList", tables);
        model.addAttribute("lifeCycle", lifeCycle);
        model.addAttribute("lifeType", lifeType);


        System.out.println("model : "+model.getAttribute("user"));
        return "main";
    }


    @GetMapping("/main2/{servId}")
    public String detail(Model model,@PathVariable("servId") String servId) throws UnsupportedEncodingException {

        WantedDetail wantedDetail = new WantedDetail();
        ResponseEntity<String> responseEntity = apiServiceImpl.getApiDetail(wantedDetail, servId);
        WantedDetailList response = apiServiceImpl.detailParser(responseEntity.getBody());

        List<ServDetailList> tables = new ArrayList<>();

        String servNm = response.getServNm();
        String jurMnofNm = response.getJurMnofNm();
        String tgtrDtlCn = response.getTgtrDtlCn();
        String slctCritCn = response.getSlctCritCn();
        String alwServCn = response.getAlwServCn();
        String trgterIndvdlArray = response.getTrgterIndvdlArray();

        ServDetailList servDetailList = new ServDetailList(slctCritCn, jurMnofNm, tgtrDtlCn, servNm, alwServCn, trgterIndvdlArray);
        tables.add(servDetailList);

        model.addAttribute("tableList", tables);
        model.addAttribute("servId", servId);

        return "detail";
    }

    @PostMapping("/addFavorite/{servId}")
    public String addFavorite(Favorite favorite) {
        FavoriteItem favoriteItem = new FavoriteItem();


        favoriteItem.setServId(favorite.getServId());
        favoriteItem.setServId(favorite.getServDgst());
        favoriteItem.setServId(favorite.getServNm());

        System.out.println("favoriteItem : "+favoriteItem);

        //favoriteRepository.save(favoriteItem);

        return "redirect:/";
    }


}
