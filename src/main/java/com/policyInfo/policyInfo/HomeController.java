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

    /*@GetMapping("/test")
    public String apiTest(Model model, Principal principal) throws UnsupportedEncodingException {


        ResponseEntity<String> responseEntity = publicAPIParser.getAPI();
        WantedList response = publicAPIParser.parser(responseEntity.getBody());

        model.addAttribute("response", response.getServList());
        System.out.println("model : "+model);

        return "test";
    }*/

}