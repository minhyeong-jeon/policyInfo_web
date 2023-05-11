package com.policyInfo.policyInfo.member;

import com.policyInfo.policyInfo.join.UserDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "User")
public class Member{

/*회원정보를 저장하는 USER Entity*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


/*@Column(name = "area")
    private String area;*/


    @Column(name = "lifeType")
    private String lifeType;


    @Enumerated(EnumType.STRING)
    private MemberRole role; //ROLE_USER, ROLE_ADMIN


    //private String roles;

   /* public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }*/
    //cascade = CascadeType.ALL, orphanRemoval = true : 회원을 저장할 경우에 favoriteItemList에 엔티티가 담겨있으면 자동으로 insert쿼리가 나가게 설정한 것
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FavoriteItem> favoriteItemList = new ArrayList<>();

    //연관관계 편의 메소드
    public void addFavoriteItem(FavoriteItem favoriteItem) {
        favoriteItem.setUser(favoriteItem.getUser());
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


    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        System.out.println("password22222 : "+password);
        return password;
    }

    @Override
    public String getUsername() {
        System.out.println("email22222 : "+email);

        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }*/
}

