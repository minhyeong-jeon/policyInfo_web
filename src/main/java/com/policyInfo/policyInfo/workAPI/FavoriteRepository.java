package com.policyInfo.policyInfo.workAPI;

import com.policyInfo.policyInfo.member.FavoriteItem;
import com.policyInfo.policyInfo.workAPI.domain.ServList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteItem, Long> {
    //List<FavoriteItem> findByUsername(String username);

}
