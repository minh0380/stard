package com.stard.member;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stard.common.dto.ResultDto;

@RequestMapping("member")
@Controller
public class MemberController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("sign-in")
	public void signIn() {};
	
	@GetMapping("join")
	public void join(Member member) {};
	
	@PostMapping("join/save")
	public String save(Model model, Member member) {
		logger.info("member : {}", member);
		memberService.saveMember(member);
		
		ResultDto<?> dto = ResultDto.builder()
				.pageTitle("알림 :: Stard · Study With Cards!")
				.useModal(true)
				.modalTitle("회원가입 완료")
				.modalBody("회원가입이 완료되었습니다.")
				.primaryBtn("확인")
				.primaryUrl("/")
				.useSecondaryBtn(false)
				.build();
		
		model.addAttribute("page", dto);
		
		return "common/result";
	}

}
