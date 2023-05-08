package com.policyInfo.policyInfo.workAPI.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ServDetailList {

    public Long id;

    public String slctCritCn;
    public String jurMnofNm;          // 단체
    public String tgtrDtlCn;          //생애주기
    public String servDgst;           // 내용
    public String servDtlLink;        // 링크
    public String servNm;             // 제목
    public String servId;             // 서비스 ID
    public String alwServCn;
    public String trgterIndvdlArray;  // 대상


    public WantedList wantedList;

    public ServDetailList(String slctCritCn, String jurMnofNm, String tgtrDtlCn, String servNm, String alwServCn, String trgterIndvdlArray) {
        this.slctCritCn = slctCritCn;
        this.jurMnofNm = jurMnofNm;
        this.tgtrDtlCn = tgtrDtlCn;
        this.servNm = servNm;
        this.alwServCn = alwServCn;
        this.trgterIndvdlArray = trgterIndvdlArray;
    }

}
