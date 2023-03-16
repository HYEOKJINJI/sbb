package com.mysite.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.board.Entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
