package com.policyInfo.policyInfo.workAPI;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServList {

    public Integer inqNum;
    public String jurMnofNm;          // 단체
    public String jurOrgNm;
    public String lifeArray;          //생애주기
    public String servDgst;           // 내용
    public String servDtlLink;        // 링크
    public String servNm;             // 제목
    public String servId;             // 서비스 ID
    public Integer svcfrstRegTs;
    public String trgterIndvdlArray;  // 대상

    public ServList(String servNm, String servDgst) {
        this.servNm = servNm;
        this.servDgst = servDgst;
    }

}
