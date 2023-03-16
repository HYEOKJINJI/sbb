package com.mysite.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.board.Entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	Question findBySubjectAndContent(String subject, String content);
	Page<Question> findAll(Specification<Question> spec, Pageable pageable);
	
	Question findBySubject(String subject);	

	Question findByContent(String content);
	
	List<Question> findBySubjectLike(String subject);
	
	List<Question> findByContentLike(String content);
	
	List<Question> findBySubjectLikeOrContentLike (String subject, String content);
	
	List<Question> findBySubjectLikeOrderByCreateDateAsc(String subject);
	
	List<Question> findBySubjectLikeOrderByCreateDateDesc(String subject);
	
	List<Question> findAllByOrderByCreateDateAsc();
	
	List<Question> findAllByOrderByCreateDateDesc();
	
	Page<Question> findAll(Pageable pageable);

}
