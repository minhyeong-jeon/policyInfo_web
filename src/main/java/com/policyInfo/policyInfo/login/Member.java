package com.policyInfo.policyInfo.login;

import com.policyInfo.policyInfo.member.FavoriteItem;
import com.policyInfo.policyInfo.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String pswd;

    @Column(nullable = false, unique = true)
    private String email;

    private String lifeCycle;

    private String area;

    private String lifeType;

    //cascade = CascadeType.ALL, orphanRemoval = true : 회원을 저장할 경우에 favoriteItemList에 엔티티가 담겨있으면 자동으로 insert쿼리가 나가게 설정한 것
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteItem> favoriteItemList = new ArrayList<>();

    //연관관계 편의 메소드
    public void addFavoriteItem(FavoriteItem favoriteItem) {
        favoriteItem.setMember(favoriteItem.getMember());
        favoriteItemList.add(favoriteItem);
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;



    @Builder
    public Member(Long id, String name, String pswd,String email, String lifeCycle, String area, String lifeType, List<FavoriteItem> favoriteItemList, Role role) {
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

/*    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "member_role", joinColumns = {@JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private List<Role> roles;*/


/*    @Builder
    public Member(String name, String email, String pswd, String address, MemberRole role) {
        this.pswd = pswd;
        this.email = email;
        this.roles = role;
    }*/


/*    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .name(memberFormDto.getName())
                .email(memberFormDto.getEmail())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))  //암호화처리
                .role(MemberRole.USER)
                .build();
        return member;
    }*/
}
