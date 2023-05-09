package com.policyInfo.policyInfo.workAPI.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter @Setter
@Entity
@Data
@AllArgsConstructor
public class ServList {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "publicId")
    Long id;

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


    public ServList(String jurMnofNm, String jurOrgNm, String lifeArray, String servDgst, String servDtlLink, String servNm, String servId, String trgterIndvdlArray) {
        this.jurMnofNm = jurMnofNm;
        this.jurOrgNm = jurOrgNm;
        this.lifeArray = lifeArray;
        this.servDgst = servDgst;
        this.servDtlLink = servDtlLink;
        this.servNm = servNm;
        this.servId = servId;
        this.trgterIndvdlArray = trgterIndvdlArray;
    }

    public ServList(String servNm, String servDgst, String servId) {
        this.servNm = servNm;
        this.servId = servId;
        this.servDgst = servDgst;
    }

    public ServList(ServList servList) {
    }
}
