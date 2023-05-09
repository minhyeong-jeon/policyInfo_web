package com.policyInfo.policyInfo.member;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "userId", nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(name = "lifeCycle")
    private String lifeCycle;

    @Column(name = "area")
    private String area;

    @Column(name = "lifeType")
    private String lifeType;

    private String role; //ROLE_USER, ROLE_ADMIN

    //cascade = CascadeType.ALL, orphanRemoval = true : 회원을 저장할 경우에 favoriteItemList에 엔티티가 담겨있으면 자동으로 insert쿼리가 나가게 설정한 것
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FavoriteItem> favoriteItemList = new ArrayList<>();

    //연관관계 편의 메소드
    public void addFavoriteItem(FavoriteItem favoriteItem) {
        favoriteItem.setUser(favoriteItem.getUser());
        favoriteItemList.add(favoriteItem);
    }

}
