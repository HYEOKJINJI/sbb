package com.mysite.board.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.board.DateNotFoundException;
import com.mysite.board.Entity.Answer;
import com.mysite.board.Entity.Member;
import com.mysite.board.Entity.Question;
import com.mysite.board.repository.AnswerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor		// 생성자를 통한 객체 주입 : DI
@Service
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	
	// 답변글을 저장하는 메소드, Controller 에서 Question 객체를 생성해서 아규먼트로 인풋											
	/*
	 * public void create(Question question, String content) {
	 * 
	 * // Answer 객체를 생성 후 아규먼트로 넘어오는 값을 setter 주입
	 * Answer a = new Answer();
	 * a.setContent(content); a.setCreateDate(LocalDateTime.now());
	 * a.setQuestion(question);
	 * 
	 * this.answerRepository.save(a); }
	 */
	
	public Answer create(Question question, String content, Member author) {
		
		Answer answer = new Answer();
		
		
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author);
		
		this.answerRepository.save(answer);
		
		return answer;
	}
	
	public Answer getAnswer(Integer id) {
		
		Optional<Answer> answer = this.answerRepository.findById(id);
		
		if(answer.isPresent()) {
			return answer.get();
		}else {
			throw new DateNotFoundException("failed");
		}
	}
	
	public void modify(Answer answer, String content) {
		
		System.out.println("기존의 닶변을 수정함");
		
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		this.answerRepository.save(answer);
		
	}
	
	public void delete(Answer answer) {
		this.answerRepository.delete(answer);
	}
	
	public void vote(Answer answer, Member member) {
		answer.getVoter().add(member);
		this.answerRepository.save(answer);
	}

}