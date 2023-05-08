package com.policyInfo.policyInfo.workAPI.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Getter @Setter
public class WantedDetailList {

/*    public Long id;
    public String totalCount = "";
    public String resultCode = "";
    public String resultMessage = "";
    public String pageNo            = "1";      //기본값 1,최대 1000
    public String numOfRows         = "100";    //출력건수*/

    public String servId;             // 서비스 ID
    public String servNm;             // 제목
    public String jurMnofNm;          // 단체
    public String tgtrDtlCn;
    public String slctCritCn;
    public String alwServCn;
    public String trgterIndvdlArray;  // 대상
    public String lifeArray;          //생애주기

    public List<ApplmetList> applmetList;
    public List<InqplCtadrList> inqplCtadrList;
    public List<InqplHmpgReldList> inqplHmpgReldList;
    public List<BasfrmList> basfrmList;
    public List<BaslawList> baslawList;

    public String resultCode;  // 대상

    public String resultMessage;  // 대상

    /*public String servDgst;           // 내용
    public String servDtlLink;        // 링크*/





}
