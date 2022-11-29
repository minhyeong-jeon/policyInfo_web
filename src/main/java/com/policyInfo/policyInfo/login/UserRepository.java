package com.policyInfo.policyInfo.login;

import com.policyInfo.policyInfo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    //List<UserInfo> findAllBy();
    User findByLifeCycle(String username);

    @Query(value = "select u.lifeCycle as lifeCycle, u.lifeType as lifeType from User u", nativeQuery = true)
    List<UserInfo> findCycleAndType();
}
