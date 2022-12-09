package com.policyInfo.policyInfo.workAPI;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Getter @Setter
public class ServList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "publicId")
    public Long id;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "publicMainId", insertable = false, updatable = false)
    public WantedList wantedList;

    public ServList(String servNm, String servDgst) {
        this.servNm = servNm;
        this.servDgst = servDgst;
    }

}
