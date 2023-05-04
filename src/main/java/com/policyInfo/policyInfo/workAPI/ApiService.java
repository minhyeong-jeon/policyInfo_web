package com.policyInfo.policyInfo.workAPI;

import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;

public interface ApiService {

    ResponseEntity<String> getAPI() throws UnsupportedEncodingException;

    WantedList parser(String xml);

    ResponseEntity<String> getApiDetail(@NonNull WantedDetail wantedDetail) throws UnsupportedEncodingException;

}
