package com.mysite.board.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.board.Entity.Member;
import com.mysite.board.Entity.Question;
import com.mysite.board.form.AnswerForm;
import com.mysite.board.form.QuestionForm;
import com.mysite.board.service.MemberService;
import com.mysite.board.service.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor 
@Controller
public class QuestionController {
	
	private final QuestionService questionService;
	private final MemberService memberService;
	
	@GetMapping("/question/list")
	public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Question> paging = this.questionService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "question_list";
    }
	

	// 상세 페이지를 처리하는 메소드 : /question/detail/1
	@GetMapping("/question/read/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {

		// 서비스 클래스의 메소드 호출 : 상세페이지 보여달라
		Question q = this.questionService.getQuestion(id);

		// Model 객체에 담아서 클라이언트에게 전송
		model.addAttribute("question", q);
		return "read"; // templates : question_detail.html

	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/question/create")
	public String questioncreate(QuestionForm questionForm) {
		return "write";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/question/create")
	public String questionCreate(
		    @Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal
		) {
		    if (bindingResult.hasErrors()) {
		        return "write";
		    }

		    Member member = this.memberService.getMember(principal.getName());
		    this.questionService.create(questionForm.getSubject(), questionForm.getContent(), member, questionForm.isSecret());
		    return "redirect:/question/list";
		}
	
    @PreAuthorize("isAuthenticated()")
    @GetMapping("question/modify/{id}")
    public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "question_form";
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("question/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("question/delete/{id}")
	public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		
		if(!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
		}
		this.questionService.delete(question);
		
		return "redirect:/";
	}
	
	// id : Question 객체
	// principal : 현재 투표하는 객체를 가지고 온다.
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("question/vote/{id}")
	public String questionVote(Principal principal, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		Member member = this.memberService.getMember(principal.getName());
		this.questionService.vote(question, member);
		return String.format("redirect:/question/detail/%s", id);
	}

}
