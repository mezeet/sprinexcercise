package main;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import spring.Member;
import spring.MemberDao;

public class Main {

	public static void main(String[] args) {
		
	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx.xml");
	MemberDao memberDao = ctx.getBean("memberDao", MemberDao.class);  
	int count = memberDao.count();
	System.out.println("총 회원 수 :"+count);
	
	List<Member> list = memberDao.list();
	
	for(Member member:list){
		System.out.println(member);
	}
	
	System.out.println("회원 정보 보기(test) ================================");
	Member member = memberDao.view("test");
	
	if(member != null){
		System.out.println(" 아이디 :" + member.getId());
		System.out.println(" 전   번:" + member.getTel());
		System.out.println(" 나   이 :" + member.getAge());
		System.out.println(" 등록일:" + member.getRegdate());
	}
	
	// 회원 가입
	Member newMember = new Member();
	newMember.setId("ded");
	newMember.setPw("1111");
	newMember.setName("김갑환");
	newMember.setAge(30);
	newMember.setTel("010-4444-5555");
	newMember.setGrade(5);
	memberDao.write(newMember);
	
	System.out.println("회원 리스트 ========================");
	list = memberDao.list();
	
	for(Member newmember:list){
		System.out.println(newmember);
	}
	
	ctx.close();
	}
	

}
