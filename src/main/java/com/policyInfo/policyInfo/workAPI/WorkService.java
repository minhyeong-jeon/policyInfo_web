package com.policyInfo.policyInfo.workAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkService{

    @Value("${openapi.service.key}")
    String OPENAPI_KEY;

    @Value("${openapi.service.url}")
    String OPENAPI_URL;

    public ResponseEntity<String> getAPi(){
        //String url = OPENAPI_URL + "?serviceKey=" + OPENAPI_KEY;
        String url = "https://www.bokjiro.go.kr/ssis-tbu/NationalWelfareInformations/NationalWelfarelist.do?serviceKey=CBcCuS23DUJC6B2TfVraEjO8ReHrJF%2FoZfCEn3NxEiKhWB2Of81t%2B9At922MLrK6%2FwDNrVgIE35dMSFpUVKHXA%3D%3D&callTp=L&srchKeyCode=001&SG_APIM=2ug8Dm9qNBfD32JLZGPN64f3EoTlkpD8kSOHWfXpyrY";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(URI.create(url), HttpMethod.GET, entity, String.class);

        return response;
    }

    public WantedList parser(String xml) {
        //ObjectMapper xmlMapper = new XmlMapper();
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        WantedList response = null;
        try {
            response = xmlMapper.readValue(xml, WantedList.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
