package com.stard.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService {
	
	private final MemberRepository memberRepository;
	
	@Autowired // bcrypt를 spring이 MemberService에 넣어줌. 이거 안해주면 encoder가 null이라서 에러남.
	private PasswordEncoder encoder;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByUserIdAndIsLeave(username, false);
		return new MemberAccount(member);
	}
	
	public void saveMember(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberRepository.save(member);
	}

}
