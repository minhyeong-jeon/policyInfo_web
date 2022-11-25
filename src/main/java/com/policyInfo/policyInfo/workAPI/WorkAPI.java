package com.policyInfo.policyInfo.workAPI;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class WorkAPI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jurMnofNm;
    private String jurOrgNm;
    private String servDgst;
    private String servDtlLink;
    private String servId;
    private String servNm;
    private String trgterIndvdlArray;

    public WorkAPI(Long id, String jurMnofNm, String jurOrgNm, String servDgst, String servDtlLink, String servId, String servNm, String trgterIndvdlArray) {
        this.id = id;
        this.jurMnofNm = jurMnofNm;
        this.jurOrgNm = jurOrgNm;
        this.servDgst = servDgst;
        this.servDtlLink = servDtlLink;
        this.servId = servId;
        this.servNm = servNm;
        this.trgterIndvdlArray = trgterIndvdlArray;
    }
}

