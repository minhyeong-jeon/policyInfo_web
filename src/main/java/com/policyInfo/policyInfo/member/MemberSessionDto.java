package com.policyInfo.policyInfo.member;

import com.policyInfo.policyInfo.login.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberSessionDto {

    private String name;
    private String pswd;
    private String email;
    private String lifeCycle;

    private String area;

    private String lifeType;
    private Role role;

    private List<FavoriteItem> favoriteItemList = new ArrayList<>();

    /* Entity -> Dto */
    public MemberSessionDto(Member member) {
        this.name = member.getName();
        this.pswd = member.getPswd();
        this.email = member.getEmail();
        this.lifeCycle = member.getLifeCycle();
        this.area = member.getArea();
        this.lifeType = member.getLifeType();
        this.favoriteItemList = member.getFavoriteItemList();
        this.role = member.getRole();
    }

}
