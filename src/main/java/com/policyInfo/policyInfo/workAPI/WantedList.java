package com.policyInfo.policyInfo.workAPI;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

//@Entity
//@Getter @Setter
//@RequiredArgsConstructor
@Data
@NoArgsConstructor
public class WantedList {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //public Long workId;
    //public String callTp            = "L";      //페이지타입 L:목록 D상세
    public String totalCount = "";
    public String resultCode = "";
    public String resultMessage = "";
    public String pageNo            = "1";      //기본값 1,최대 1000
    public String numOfRows         = "100";    //출력건수
    public List<ServList> servList;
    //public String srchKeyCode       = "003";    //001제목 002내용 003제목+내용

    /*public String searchWrd         = "";      //★ 검색어
    public String lifeArray         = "";       //★ 생애주기
    public String charTrgterArray   = "";       //대상특성
    public String obstKiArray       = "";       //장애유형
    public String obstAbtArray      = "";       //장애정도
    public String trgterIndvdlArray = "";       //★ 가구유형
    public String desireArray       = "";       //★ 사업목적

    public String servId       = "";       //★ 사업목적*/
}
