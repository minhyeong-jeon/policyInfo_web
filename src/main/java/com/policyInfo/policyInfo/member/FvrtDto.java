package com.policyInfo.policyInfo.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FvrtDto {

    private Long id;
    private String servNm;
    private String servDgst;
    private String servId;
    private Member member;

    /* Dto -> Entity */
    public FavoriteItem toEntity() {
        FavoriteItem favoriteItem = FavoriteItem.builder()
        .id(id)
        .servNm(servNm)
        .servDgst(servDgst)
        .servId(servId)
        .member(member)
        .build();

        return favoriteItem;
    }
}
