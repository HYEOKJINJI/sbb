package com.mysite.board.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DetailController {
	
	@PreAuthorize("isAuthenticated")	// 권한주기
	@GetMapping("/detail")
	public String detail() {
		return "detail1";
	}

}
