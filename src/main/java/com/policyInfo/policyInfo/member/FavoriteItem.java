package com.policyInfo.policyInfo.member;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Data
public class FavoriteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favoriteId")
    private Long id;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "itemCntn")
    private String itemCntn;

    @Column(name = "closeDt")
    private String closeDt;

    @Column(name = "servId")
    private String servId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

}

