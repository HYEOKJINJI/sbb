package com.mysite.board.role;

import lombok.Getter;
import lombok.Setter;

@Getter 
public enum MemberRole {
	
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	MemberRole(String value){
		this.value=value;
	}
	private String value;

}
