package com.policyInfo.policyInfo.member;

import com.policyInfo.policyInfo.workAPI.ServList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
