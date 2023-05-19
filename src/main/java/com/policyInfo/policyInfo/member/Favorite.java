package com.policyInfo.policyInfo.member;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    private String servNm;

    private String servDgst;

    private String servId;


    public Favorite(String servId) {
        this.servId = servId;
    }
}

