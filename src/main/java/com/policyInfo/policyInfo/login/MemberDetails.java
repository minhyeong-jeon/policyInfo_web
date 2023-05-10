/*

package com.policyInfo.policyInfo.login;

import com.policyInfo.policyInfo.member.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

*/
/* UserDetails 인터페이스 : 스프링 시큐리티에서 회원의 정보를 담기 위해서 사용하는 인터페이스
 *  스프링 시큐리티에서 제공하는 User 클래스를 사용 *//*


@Data
public class MemberDetails implements UserDetails {

    //private final User user;

    public MemberDetails(User user) {
        this.user = user;
    }

    */
/*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

/* 첫번째 방법
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;*//*
*/
/*


        *//*

*/
/* 두번째 방법
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(()->{ return user.getRole();});
        return collect;*//*
*/
/*


        *//*

*/
/* 세번째 방법
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new SimpleGrantedAuthority(user.getRole()));
        return collect;


        return Collections.singleton(
                new SimpleGrantedAuthority(user.getRole())
        );
    }*//*

    private User user;


    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = user.getRole().toString();
        System.out.println("role : "+role);

        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        });
        System.out.println("collect : "+collect);
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
*/
