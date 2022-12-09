package com.policyInfo.policyInfo.workAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServAPIRepository extends JpaRepository<ServList, Long> {

    List<ServList> findByLifeArrayAndTrgterIndvdlArray(String LifeArray, String TrgterIndvdlArray);
    List<ServList> findByLifeArrayContaining(String LifeArray);

    List<ServList> findByLifeArrayContainingAndTrgterIndvdlArrayContaining(@Nullable String LifeArray, String TrgterIndvdlArray);

    List<ServList> findByTrgterIndvdlArrayContaining(String TrgterIndvdlArray);
}
