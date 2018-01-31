package com.thirty.api.persistence;

import com.thirty.api.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by ByeongChan on 2018. 1. 16..
 */

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUniqueKey(String uniqueKey);

    @Query(value = "SELECT * FROM member WHERE status='WAITING' ORDER BY rand() LIMIT 1", nativeQuery = true)
    Member randomSampling();
}
