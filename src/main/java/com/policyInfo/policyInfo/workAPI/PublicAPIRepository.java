package com.policyInfo.policyInfo.workAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicAPIRepository extends JpaRepository<WantedList, Long> {

    //List<WantedList> findResultMessage();

}
