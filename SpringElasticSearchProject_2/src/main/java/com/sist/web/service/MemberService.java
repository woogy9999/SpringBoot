package com.sist.web.service;
import com.sist.web.dao.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.vo.*;
import com.sist.web.service.*;
// JPA방식 => 1. return형 2. 매개변수 3. 메소드명 -> 자동지정
// WHERE 문장 => findBy 변수명명령어 where name like=< Less Then , graterr then
// SQL 문장이 필요시에는 반드시 native query 

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	public void saveMember(Member member)
	{
		memberRepository.save(member);
	}
	public void deleteMember(Member member)
	{
		memberRepository.delete(member);
	}
	public Iterable<Member> getAllMembers()
	{
		return memberRepository.findAll();
	}
	public Member getMemberId(String id)
	{
		return memberRepository.findById(id).orElse(null);
	}
}
