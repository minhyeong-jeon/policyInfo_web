package com.policyInfo.policyInfo.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);

    Member findByEmail(String email);

    //User findByNickName(String username);

    //Optional<User> findByEmail(String email);
}
