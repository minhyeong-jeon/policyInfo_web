package com.policyInfo.policyInfo;

import org.springframework.data.jpa.repository.Query;

import javax.swing.text.Document;
import java.util.List;

public interface UserInfo {
    String getLifeCycle();
    String getLifeType();
    String getEmail();
}
