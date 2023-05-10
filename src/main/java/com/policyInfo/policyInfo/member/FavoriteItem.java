package com.policyInfo.policyInfo.member;

import com.policyInfo.policyInfo.workAPI.domain.ServList;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class FavoriteItem {

    //유저id, 즐겨찾기 id, 서비스명, 서비스내용, 서비스id

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "favoriteId")
    private Long id;

    @Column(name = "servNm")
    private String servNm;

    @Column(name = "servDgst")
    private String servDgst;

    @Column(name = "servId")
    private String servId;

    @ManyToOne
    @JoinColumn(name = "username")
    private Member user;

}

