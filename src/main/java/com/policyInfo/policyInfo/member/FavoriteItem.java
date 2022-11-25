package com.policyInfo.policyInfo.member;

import com.policyInfo.policyInfo.login.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class FavoriteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long id;

    private String itemName;

    private String itemCntn;

    private String closeDt;

    private String servId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}

