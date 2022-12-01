package com.policyInfo.policyInfo.workAPI;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class WantedList {

    public String totalCount = "";
    public String resultCode = "";
    public String resultMessage = "";
    public String pageNo            = "1";      //기본값 1,최대 1000
    public String numOfRows         = "100";    //출력건수
    public List<ServList> servList;

}
