/*
package com.policyInfo.policyInfo.workAPI;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Getter
@Setter
@RequiredArgsConstructor
public class PublicDataList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long policyId;
    public String jurMnofNm;          // 단체
    public String lifeArray;          //생애주기
    public String servDgst;           // 내용
    public String servDtlLink;        // 링크
    public String servNm;             // 제목
    public String trgterIndvdlArray;  // 대상
    public String servID;             // 서비스 ID

    public PublicDataList(String jurMnofNm, String lifeArray, String servDgst, String servDtlLink, String servNm, String trgterIndvdlArray, String servID) {
        this.jurMnofNm = jurMnofNm;
        this.lifeArray = lifeArray;
        this.servDgst = servDgst;
        this.servDtlLink = servDtlLink;
        this.servNm = servNm;
        this.trgterIndvdlArray = trgterIndvdlArray;
        this.servID = servID;
    }

    public void SetEmpty(){
        jurMnofNm = "";
        lifeArray = "";
        servDgst= "";
        servDtlLink = "";
        servNm = "";
        trgterIndvdlArray = "";
        servID = "";
    }
}
*/
