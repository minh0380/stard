package com.stard.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.stard.common.dto.ResultDto;

@Controller
public class IndexContoller {
	
	@GetMapping("/")
	public String index() {
		return "redirect:/member/sign-in";
	}
	
	@GetMapping("/common/result")
	public String common(Model model) {
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
