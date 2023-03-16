package com.mysite.board.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mysite.board.dto.MemberForm;
import com.mysite.board.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	
	@GetMapping("/member/login")
	public String login(){
		return "login";
	}
	
	@GetMapping("/member/create")
	public String create(MemberForm memberForm) {
		return "signup";
	}
	
	@PostMapping("/member/create")
	public String createMember(@Valid MemberForm memberForm, BindingResult bindingResult) {
		String birthday = memberForm.birthday1+memberForm.birthday2+memberForm.birthday3;
		if(bindingResult.hasErrors()) {
			return "signup";
		}
		if(!memberForm.getPassword1().equals(memberForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "두개의 패스워드가 일치하지 않습니다.");
			return "signup";
		}
		try {
			memberService.createMember(memberForm.getUsername(), memberForm.getPassword1(), memberForm.getMname(), birthday, memberForm.getPhonenum(), memberForm.getAddr());
			
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("singupFailed", "이미 등록된 사용자 입니다.");
			return "signup";
		}catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("singupFaild", e.getMessage());
			return "signup";
		}
		return "login";
	}

}
