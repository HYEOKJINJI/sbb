package com.mysite.board.Entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 4000)
	private String content;

	private LocalDateTime createDate;	
	
	private LocalDateTime modifyDate;
	
	@ManyToOne		
	private Question question; 
	
	@ManyToOne		
	private Member author;
	
	@ManyToMany
	Set<Member> voter;

	
	 
}