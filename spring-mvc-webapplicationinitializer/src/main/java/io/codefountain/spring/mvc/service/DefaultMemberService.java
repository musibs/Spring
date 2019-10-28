package io.codefountain.spring.mvc.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.codefountain.spring.mvc.domain.Member;
import io.codefountain.spring.mvc.exceptions.MemberAlreadyExistsException;
import io.codefountain.spring.mvc.exceptions.MemberNotFoundException;

@Service
public class DefaultMemberService implements MemberService{
	
	private List<Member> members = new LinkedList<Member>();

	
	public DefaultMemberService() {
		members.add(new Member("M1", "Somnath", "Musib"));
		members.add(new Member("M2", "John", "Doe"));
		members.add(new Member("M3", "Steven", "Smith"));
	}
	@Override
	public List<Member> list() {
		return members;
	}

	@Override
	public Member find(String memberId) {
		Optional<Member> result = members.stream().filter(member -> Objects.equals(memberId, member.getMemberId())).findFirst();
		if(result.isPresent()) {
			return result.get();
		}
		
		throw new MemberNotFoundException(memberId, "No such memeber found");
	}

	@Override
	public void add(Member member) {
		Objects.requireNonNull(member);
		Member existingMember = null;
		try{
			existingMember = find(member.getMemberId());
		}
		catch(MemberNotFoundException mnf) {
			members.add(member);
			return;
		}
		throw new MemberAlreadyExistsException(existingMember, "Member Already exists");
	}

	@Override
	public void delete(String memberId) {
		Member existingMember = find(memberId);
		if(Objects.nonNull(existingMember)) {
			return;
		}
		members.remove(existingMember);
	}

}
