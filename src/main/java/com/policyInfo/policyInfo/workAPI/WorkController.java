/*
package com.policyInfo.policyInfo.workAPI;

import groovy.transform.AutoImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;


@Controller
@RequestMapping("/")
public class WorkController {

    @Autowired
    WorkService workService;

    public WorkController(WorkService workService){
        this.workService = workService;
    }

    @GetMapping("/covid19")
    public String getCovid19(Model model) throws UnsupportedEncodingException {
        ResponseEntity<String> responseEntity = workService.getAPI();
        WantedList response = workService.parser(responseEntity.getBody());

        //System.out.println("response" + response);
        //System.out.println("response.totalCount" + response.getTotalCount());

        model.addAttribute("response",response.getServList().get(0));
        return "xmlParsing";
    }
}
*/
