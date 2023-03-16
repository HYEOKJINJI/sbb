package com.mysite.board;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//사용자가 생성한 예외 : 실행시 처리
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "요청한 정보를 찾지 못했습니다.")
public class DateNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DateNotFoundException (String message) {
		super(message);
	}
}
