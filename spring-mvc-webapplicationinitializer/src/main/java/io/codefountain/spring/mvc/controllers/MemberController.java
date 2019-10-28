package io.codefountain.spring.mvc.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.codefountain.spring.mvc.domain.Member;
import io.codefountain.spring.mvc.exceptions.InvalidMemberIdException;
import io.codefountain.spring.mvc.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/members")
	public void addForm() {}
	
	@PostMapping("/members")
	public ModelAndView add(@RequestParam("memberId") String memberId, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
		
		Member member = new Member(memberId, firstName, lastName);
		memberService.add(member);
		List<Member> members = memberService.list();
		
		ModelAndView modelAndView = new ModelAndView("members");
		modelAndView.addObject("members", members);
		return modelAndView;
		
	}
	
	@GetMapping("/members/{memberId}")
	public ModelAndView getMember(@PathVariable("memberId") String memberId) {
		if(Objects.nonNull(memberId) && !memberId.startsWith("M")) {
			throw new InvalidMemberIdException("Member Id should start with M");
		} 
		ModelAndView modelAndView = new ModelAndView("member");
		Member member = memberService.find(memberId);
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	
	@ExceptionHandler(InvalidMemberIdException.class)
	public ModelAndView handleInvalidMemberId(InvalidMemberIdException ex) {
		ModelAndView modelAndView = new ModelAndView("invalidMemberId");
		modelAndView.addObject("exception", ex);
		return modelAndView;
	}
}
