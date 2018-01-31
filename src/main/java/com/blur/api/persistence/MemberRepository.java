package com.blur.api.persistence;

import com.blur.api.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by ByeongChan on 2018. 1. 16..
 */

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUniqueKey(String uniqueKey);

    @Query(value = "SELECT * FROM member WHERE status=0 ORDER BY rand() LIMIT 1", nativeQuery = true)
    Member randomSampling();
}