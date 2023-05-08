package com.policyInfo.policyInfo.workAPI;

import com.policyInfo.policyInfo.member.FavoriteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteItem, Long> {

}
