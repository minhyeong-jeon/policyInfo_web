package com.policyInfo.policyInfo;

import com.policyInfo.policyInfo.member.User;
import com.policyInfo.policyInfo.member.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

   /* @Autowired
    WorkRepository workRepository;
*/
    @GetMapping("/user")
    public @ResponseBody String user(Model model) {
        return "login";
    }

    @GetMapping("/")
    public String board(Model model, User user, Principal principal) {
        String lifeCycle = userRepository.findByUsername(principal.getName()).getLifeCycle();
        String lifeType = userRepository.findByUsername(principal.getName()).getLifeType();

        if(lifeCycle == null && lifeType==null){
            lifeCycle = "선택안함";
            lifeType = "선택안함";
        }
        else {
            model.addAttribute("lifeCycle", lifeCycle);
            model.addAttribute("lifeType", lifeType);
        }

        /*WantedList wantedList = new WantedList();
        wantedList.setTrgterIndvdlArray(userRepository.findByUsername(principal.getName()).getLifeType());
        wantedList.setLifeArray(userRepository.findByUsername(principal.getName()).getLifeCycle());
        workRepository.save(wantedList);*/

        System.out.println("lifeCycle = " + lifeCycle);

        return "main";
    }

}