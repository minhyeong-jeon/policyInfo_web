package com.policyInfo.policyInfo.member;

import com.policyInfo.policyInfo.login.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@ToString
public class MemberDto {
    private Long id;
    private String name;

    private String pswd;

    private String email;

    private String lifeCycle;

    private String area;

    private String lifeType;

    private List<FavoriteItem> favoriteItemList = new ArrayList<>();

    private Role role;

    /* DTO -> Entity */
    public Member toEntity() {
        Member member = Member.builder()
                .name(name)
                .pswd(pswd)
                .email(email)
                .lifeCycle(lifeCycle)
                .area(area)
                .lifeType(lifeType)
                .favoriteItemList(favoriteItemList)
                .role(role.MEMBER)
                .build();
        return member;
    }

    @Builder
    public MemberDto(Long id, String name, String pswd, String email, String lifeCycle, String area, String lifeType, List<FavoriteItem> favoriteItemList, Role role) {
        this.id = id;
        this.name = name;
        this.pswd = pswd;
        this.email = email;
        this.lifeCycle = lifeCycle;
        this.area = area;
        this.lifeType = lifeType;
        this.favoriteItemList = favoriteItemList;
        this.role = role;

    }
}