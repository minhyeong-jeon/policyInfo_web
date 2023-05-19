package com.policyInfo.policyInfo.workAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.policyInfo.policyInfo.member.UserRepository;
import com.policyInfo.policyInfo.workAPI.domain.ServDetailList;
import com.policyInfo.policyInfo.workAPI.domain.WantedDetail;
import com.policyInfo.policyInfo.workAPI.domain.WantedDetailList;
import com.policyInfo.policyInfo.workAPI.domain.WantedList;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    ServAPIRepository servAPIRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<String> getAPI() throws UnsupportedEncodingException {
        //String url = OPENAPI_URL + "?serviceKey=" + OPENAPI_KEY;
        String desireArray = null;

        //String lifeCycle = "청소년";
        //String lifeType = "저소득";
        //001영유아 002아동 003청소년 004청년 005중장년 006노년 007임신·출산
/*        if(lifeCycle.equals("영유아")){
            lifeCycle ="001";
        }
        else if(lifeCycle.equals("아동")) {
            lifeCycle = "002";
        }
        else if(lifeCycle.equals("청소년")) {
            lifeCycle = "003";
        }
        else if(lifeCycle.equals("청년")) {
            lifeCycle = "004";
        }
        else if(lifeCycle.equals("중장년")) {
            lifeCycle = "005";
        }
        else if(lifeCycle.equals("노년")) {
            lifeCycle = "006";
        }
        else if(lifeCycle.equals("임신·출산")) {
            lifeCycle = "007";
        }
        else if(lifeCycle.equals("선택안함")) {
            lifeCycle = "";
        }

        //010다문화·탈북민 020다자녀 030보훈대상자 040장애인 050저소득 060한부모·조손
        if(lifeType.equals("다문화·탈북민")){
            lifeType ="010";
        }
        else if(lifeType.equals("다자녀")) {
            lifeType = "020";
        }
        else if(lifeType.equals("보훈대상자")) {
            lifeType = "030";
        }
        else if(lifeType.equals("장애인")) {
            lifeType = "040";
        }
        else if(lifeType.equals("저소득")) {
            lifeType = "050";
        }
        else if(lifeType.equals("한부모·조손")) {
            lifeType = "060";
        }
        else if(lifeType.equals("선택안함")) {
            lifeType = "";
        }*/

        //System.out.println("lifeCycle = " + lifeCycle);
        //System.out.println("lifeType = " + lifeType);
        StringBuilder urlBuilder = new StringBuilder("https://www.bokjiro.go.kr/ssis-tbu/NationalWelfareInformations/NationalWelfarelist.do"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=CBcCuS23DUJC6B2TfVraEjO8ReHrJF%2FoZfCEn3NxEiKhWB2Of81t%2B9At922MLrK6%2FwDNrVgIE35dMSFpUVKHXA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("callTp", "UTF-8") + "=" + URLEncoder.encode("L", "UTF-8")); /*호출할 페이지 타입을 반드시 설정합니다.(L: 목록, D:상세)*/
        urlBuilder.append("&" + URLEncoder.encode("srchKeyCode", "UTF-8") + "=" + URLEncoder.encode("001", "UTF-8")); /*001 제목 002 내용 003 제목+내용*/
        urlBuilder.append("&" + URLEncoder.encode("SG_APIM", "UTF-8") + "=" + URLEncoder.encode("2ug8Dm9qNBfD32JLZGPN64f3EoTlkpD8kSOHWfXpyrY", "UTF-8")); /*001 제목 002 내용 003 제목+내용*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("400", "UTF-8")); /*출력건수, 기본값 10, 최대 500 까지 가능합니다.*/

/*        if(!lifeCycle.isEmpty())
            urlBuilder.append("&" + URLEncoder.encode("lifeArray","UTF-8") + "=" + URLEncoder.encode(lifeCycle, "UTF-8")); *//*생애주기*//*
        if(!lifeType.isEmpty())
            urlBuilder.append("&" + URLEncoder.encode("trgterIndvdlArray","UTF-8") + "=" + URLEncoder.encode(lifeType, "UTF-8")); *//*대상자*/
/*        if(!desireArray.isEmpty())
            urlBuilder.append("&" + URLEncoder.encode("desireArray","UTF-8") + "=" + URLEncoder.encode(desireArray, "UTF-8")); *//*사업목적*/


        System.out.println("urlBuilder4 = " + urlBuilder);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(URI.create(String.valueOf(urlBuilder)), HttpMethod.GET, entity, String.class);

        //System.out.println("response.getBody() = " + response.getBody());
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

        //System.out.println("response = " + response);

        return response;
    }

    public ResponseEntity<String> getApiDetail(@NonNull WantedDetail wantedDetail, String servId) throws UnsupportedEncodingException {

        StringBuilder urlDetailBuilder = new StringBuilder("https://www.bokjiro.go.kr/ssis-tbu/NationalWelfareInformations/NationalWelfaredetailed.do"); /*URL*/
        urlDetailBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=%2BWnjcadNxjH3FFyaHjifaa6i%2Fi3l9YuKKNF1N1NHsyUESdHZm8EY1NYJv690quMUhZ7NQXKfyW4jQW%2FhuiF37A%3D%3D"); /*Service Key*/
        urlDetailBuilder.append("&" + URLEncoder.encode("callTp", "UTF-8") + "=" + URLEncoder.encode(wantedDetail.callTp, "UTF-8")); /*호출할 페이지 타입을 반드시 설정합니다.(L: 목록, D:상세)*/
        urlDetailBuilder.append("&" + URLEncoder.encode("servId", "UTF-8") + "=" + servId); /*서비스아이디*/
        urlDetailBuilder.append("&" + URLEncoder.encode("SG_APIM", "UTF-8") + "=2ug8Dm9qNBfD32JLZGPN64f3EoTlkpD8kSOHWfXpyrY");

        System.out.println("urlDetailBuilder = " + urlDetailBuilder);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(URI.create(String.valueOf(urlDetailBuilder)), HttpMethod.GET, entity, String.class);

        return response;
    }

    public WantedDetailList detailParser(String xml) {
        //ObjectMapper xmlMapper = new XmlMapper();
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        WantedDetailList response = null;
        try {
            response = xmlMapper.readValue(xml, WantedDetailList.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return response;
    }

    public ServDetailList detail(String servId) {
        WantedDetail wantedDetail = new WantedDetail();
        ServDetailList servDetailList = null;
        try {
            ResponseEntity<String> responseEntity = getApiDetail(wantedDetail, servId);
            WantedDetailList response = detailParser(responseEntity.getBody());

            List<ServDetailList> tables = new ArrayList<>();

            String servNm = response.getServNm();
            String jurMnofNm = response.getJurMnofNm();
            String tgtrDtlCn = response.getTgtrDtlCn();
            String slctCritCn = response.getSlctCritCn();
            String alwServCn = response.getAlwServCn();
            String trgterIndvdlArray = response.getTrgterIndvdlArray();

            servDetailList = new ServDetailList(slctCritCn, jurMnofNm, tgtrDtlCn, servNm, alwServCn, trgterIndvdlArray);
        }
        catch (Exception e){

        }


        return servDetailList;
    }

}