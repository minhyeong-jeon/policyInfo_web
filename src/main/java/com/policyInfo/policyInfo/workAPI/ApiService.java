package com.policyInfo.policyInfo.workAPI;

import com.policyInfo.policyInfo.workAPI.domain.ServDetailList;
import com.policyInfo.policyInfo.workAPI.domain.WantedDetail;
import com.policyInfo.policyInfo.workAPI.domain.WantedDetailList;
import com.policyInfo.policyInfo.workAPI.domain.WantedList;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;

public interface ApiService {

    ResponseEntity<String> getAPI() throws UnsupportedEncodingException;

    WantedList parser(String xml);

    ResponseEntity<String> getApiDetail(@NonNull WantedDetail wantedDetail, String servId) throws UnsupportedEncodingException;

    WantedDetailList detailParser(String xml);

    ServDetailList detail(String servId);

}
