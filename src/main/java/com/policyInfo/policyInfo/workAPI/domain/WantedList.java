package com.policyInfo.policyInfo.workAPI.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class WantedList {

    public Long id;
    public String totalCount = "";
    public String resultCode = "";
    public String resultMessage = "";
    public String pageNo            = "1";      //기본값 1,최대 1000
    public String numOfRows         = "100";    //출력건수

    public List<ServList> servList;

}
