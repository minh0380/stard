package com.stard.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	
	Member findByUserIdAndIsLeave(String userId, boolean isLeave);

}
