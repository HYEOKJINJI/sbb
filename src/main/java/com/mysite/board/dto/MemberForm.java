package com.mysite.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
	
	@NotBlank(message="사용자 아이디는 필수 입력입니다.")
	public String username;
	
	@NotBlank(message="사용자 비밀번호는 필수 입력입니다.")
	public String password1;
	
	@NotBlank(message="사용자 비밀번호는 필수 입력입니다.")
	public String password2;
	
	@NotBlank(message="사용자 이름은 필수 입력입니다.")
	public String Mname;
	
	
	@NotBlank(message="사용자 주소는 필수 입력입니다.")
	public String addr;
	
	public Integer phonenum;
	
	@NotBlank(message="사용자 생년월일은 필수 입력입니다.")
	public String birthday1;
	
	@NotBlank(message="사용자 생년월일은 필수 입력입니다.")
	public String birthday2;
	
	@NotBlank(message="사용자 생년월일은 필수 입력입니다.")
	public String birthday3;

}
