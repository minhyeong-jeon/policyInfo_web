package com.policyInfo.policyInfo.workAPI;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
public class ServList {

    //public List<ServListItem> items;
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

}