package com.policyInfo.policyInfo.member;

import com.policyInfo.policyInfo.join.UserDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Member implements Serializable {

    /*회원정보를 저장하는 USER Entity*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "lifeCycle")
    private String lifeCycle;

    @Column(name = "lifeType")
    private String lifeType;


    @Enumerated(EnumType.STRING)
    private MemberRole role; //ROLE_USER, ROLE_ADMIN

    //cascade = CascadeType.ALL, orphanRemoval = true : 회원을 저장할 경우에 favoriteItemList에 엔티티가 담겨있으면 자동으로 insert쿼리가 나가게 설정한 것
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FavoriteItem> favoriteItemList = new ArrayList<>();

    //연관관계 편의 메소드
    public void addFavoriteItem(FavoriteItem favoriteItem) {
        favoriteItem.setMember(favoriteItem.getMember());
        favoriteItemList.add(favoriteItem);
    }

   @Builder
    public Member(String username, String email, String password, String lifeCycle, String lifeType, MemberRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.lifeCycle = lifeCycle;
        this.lifeType = lifeType;
        this.role = role;
    }

    public static Member createMember(UserDto userDto, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))  //암호화처리
                .lifeCycle(userDto.getLifeCycle())
                .lifeType(userDto.getLifeType())
                .role(MemberRole.USER)
                .build();
        return member;
    }
}

