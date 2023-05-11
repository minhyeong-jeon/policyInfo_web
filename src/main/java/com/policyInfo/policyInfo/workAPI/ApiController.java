package com.policyInfo.policyInfo.workAPI;

import com.policyInfo.policyInfo.member.*;
import com.policyInfo.policyInfo.workAPI.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityManager;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class ApiController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    ServAPIRepository servAPIRepository;

    @Autowired
    ApiService apiServiceImpl;



    @GetMapping("/")
    @Transactional
    public String board(Model model, Principal principal) throws UnsupportedEncodingException {

        String lifeCycle, lifeType;
        List<ServList> servLists = new ArrayList<>();

        //ResponseEntity<String> responseEntity = apiServiceImpl.getAPI();
        //WantedList response = apiServiceImpl.parser(responseEntity.getBody());

        if(principal != null) {

            //사용자의 생애주기와 가구유형 가져오기
            lifeCycle = userRepository.findByEmail(principal.getName()).getLifeCycle();
            lifeType = userRepository.findByEmail(principal.getName()).getLifeType();

            model.addAttribute("user", principal);

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

            /*List<FavoriteItem> favoriteItemList = favoriteRepository.findByUsername(principal.getName());
            List<String> servIdList = new ArrayList<>();

            System.out.println("favoriteItemList : "+favoriteItemList);
*/
/*            String fvrtServId;
            if(favoriteItemList != null){
                for (int i = 0; i < favoriteItemList.size(); i++) {
                    fvrtServId = favoriteItemList.get(i).getServId();
                    servIdList.add(fvrtServId);
                    System.out.println("fvrtServId : "+fvrtServId);
                }
                model.addAttribute("fvrtServId", servIdList);

            }*/


        }
        else {
            lifeCycle = "선택안함";
            lifeType = "선택안함";
            servLists = servAPIRepository.findAll();
        }

        List<ServList> tables = new ArrayList<>();

        String servNm;
        String servDgst;
        String servId;

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
    private final EntityManager em;

    @PostMapping("/addFavorite/{servId}")
    public String addFavorite(Favorite favorite, Principal principal, FvrtDto fvrtDto) {

        System.out.println("principal.getName() : "+principal.getName());

        Member member = userRepository.findByEmail(principal.getName());

        System.out.println("member: "+member);

        fvrtDto.setMember(member);
        fvrtDto.setServId(favorite.getServId());
        fvrtDto.setServDgst(favorite.getServDgst());
        fvrtDto.setServNm(favorite.getServNm());

        FavoriteItem favoriteItem = fvrtDto.toEntity();

        favoriteRepository.save(favoriteItem);

        return "redirect:/";
    }


    @GetMapping("/api/save")
    public void apiSave() throws UnsupportedEncodingException {
        ResponseEntity<String> responseEntity = apiServiceImpl.getAPI();
        WantedList response = apiServiceImpl.parser(responseEntity.getBody());

        List<ServList> servLists = new ArrayList<>();

        servLists = response.getServList();

        String servNm;
        String servDgst;
        String servId, jurMnofNm, lifeArray, servDtlLink, trgterIndvdlArray, jurOrgNm;


        for (int i = 0; i < servLists.size(); i++) {

            servNm = servLists.get(i).getServNm();
            servDgst = servLists.get(i).getServDgst();
            jurMnofNm = servLists.get(i).getJurMnofNm();
            lifeArray = servLists.get(i).getLifeArray();
            servDtlLink = servLists.get(i).getServDtlLink();
            trgterIndvdlArray = servLists.get(i).getTrgterIndvdlArray();
            jurOrgNm = servLists.get(i).getJurOrgNm();
            servId = servLists.get(i).getServId();

            ServList servList = new ServList(jurMnofNm, jurOrgNm, lifeArray, servDgst, servDtlLink, servNm, servId, trgterIndvdlArray);
            servAPIRepository.save(servList);

        }
    }



}
