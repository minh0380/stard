package com.stard.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberRepositoryTest {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	MemberRepository memberRepository;
	
	@Test
	public void saveMember() {
		Member member = new Member();
		member.setUserId("mhcho@wisenut.co.kr");
		member.setPassword("123");
		member.setTel("01012345678");
		
		memberRepository.save(member);
	}
	
}
