package com.policyInfo.policyInfo.workAPI.domain;

import com.policyInfo.policyInfo.workAPI.domain.ServList;
import com.policyInfo.policyInfo.workAPI.domain.WantedList;
import lombok.Data;

@Data
public class Response {

    public WantedList wantedList;
    public ServList servList;
}

