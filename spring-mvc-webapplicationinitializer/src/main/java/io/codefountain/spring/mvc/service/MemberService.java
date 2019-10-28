package io.codefountain.spring.mvc.service;

import java.util.List;

import io.codefountain.spring.mvc.domain.Member;

public interface MemberService {

	public List<Member> list();
	public Member find(String memberId);
	public void add(Member member);
	public void delete(String memberId);
}
