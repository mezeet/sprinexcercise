package spring;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

// ibatis 가 SqlMapClientDao 설정을 지원하는 객체를 상속받아 사용한다.
// appCtx.xml 에서 SqlMapClientDao 객체를 주입받았고.
//  우리는 해당 객체 내부에 정의된 member.xml 정보 중
// Member 네임스페이스에 count 를 사용할 것이다.

public class MemberDao extends SqlMapClientDaoSupport{
	
		public int count(){
			// mbmer.xml 에 count id 로 정의한 쿼리를 실행한다.
			return (Integer)getSqlMapClientTemplate().queryForObject("count");
			// return (Integer)getSqlMapClientTemplate().queryForObject("Member.count");
		}
		
		
		public List<Member> list(){
			// iBatis 에 쿼리 결과를 여러 개 받아오는 queryForList("id") 메서드 호출
			return (List<Member>)getSqlMapClientTemplate().queryForList("list");	
		}
		
		public Member view(String id){
			// iBatis 에 쿼리 결과를 한 개 받아오는 queryForObject("id") 메서드 호출
			return (Member)getSqlMapClientTemplate().queryForObject("view",id);
		}
		
		public void write(Member member){
			getSqlMapClientTemplate().insert("write", member);
		}
		
		public void update(Member member){
			getSqlMapClientTemplate().update("update", member);
		}
		
		public void delete(Member member){
			getSqlMapClientTemplate().delete("delete", member);
		}
		
}
