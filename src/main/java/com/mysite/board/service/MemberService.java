package com.mysite.board.service;

import java.time.DateTimeException;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.board.Entity.Member;
import com.mysite.board.repository.MemberRepository;
import com.mysite.board.role.MemberRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public void createMember(String username, String password, String Mname, String birthday, Integer phonenum, String addr) {
		Member member = new Member();
		
		member.setUsername(username);
		member.setPassword(this.passwordEncoder.encode(password));
		member.setName(Mname);
		member.setBirthday(birthday);
		member.setPhonenum(phonenum);
		member.setAddr(addr);
		member.setRole(MemberRole.ADMIN);
		
		this.memberRepository.save(member);
	}
	
	public Member getMember(String username) {
		Optional<Member> member = this.memberRepository.findByUsername(username);
		
		if(member.isPresent()) {
			return member.get();
		}else {
			throw new DateTimeException("failed");
		}
	}
	

	
	

}
